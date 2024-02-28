package com.example.backend.timer;

import com.example.backend.common.Collections;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.filter.CarStatus;
import com.example.backend.filter.model.Filter;
import com.example.backend.filter.model.FilterToCar;
import com.example.backend.filter.service.FilterService;
import com.example.backend.setting.Key;
import com.example.backend.setting.service.ApplicationSettingService;
import com.example.backend.timer.otomoto.request.OtomotoDto;
import com.example.backend.timer.otomoto.response.*;
import com.example.backend.user.model.User;
import com.example.backend.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class OtomotoTimer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OtomotoTimer.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Long PAGE_SIZE = 32L;
    private static final String TIMER_NAME = "OtomotoTimer";

    @Value("${otomoto.search.api.url}")
    private String destinationPath;

    private final ApplicationSettingService applicationSettingService;
    private final UserService userService;
    private final FilterService filterService;

    public OtomotoTimer(ApplicationSettingService applicationSettingService, UserService userService, FilterService filterService) {
        this.applicationSettingService = applicationSettingService;
        this.userService = userService;
        this.filterService = filterService;
    }

    /**
     * Run otomoto timer synchronously.
     * fixedRated = 60 * 5 * 1000 is equal to 5 minutes delay
     * <p>
     * Timer fetches new cars for every available filter.
     * If filter is invalid, then it's being ignored.
     *
     * @throws EntityNotFoundException thrown if application setting is missing
     */
    @Scheduled(fixedRate = 60 * 5 * 1000)
    public void runTimer() throws EntityNotFoundException {
        List<User> allUsers = userService.getAll();
        if (Collections.isNullOrEmpty(allUsers))
            return;

        String query = applicationSettingService.getString(Key.OTOMOTO_QUERY_STRING);

        try {
            query = OBJECT_MAPPER.readValue(query, OtomotoDto.class).getQuery();
        } catch (JsonProcessingException e) {
            LOGGER.error("[{}] Invalid query: {}", TIMER_NAME, query);
            return;
        }

        Set<Filter> filters = allUsers.stream().flatMap((user) -> user.getFilters().stream()).collect(Collectors.toSet());
        if (Collections.isNullOrEmpty(filters))
            return;

        for (Filter filter : filters)
            loadDataForSingleFilter(filter, query);

        LOGGER.info("[{}][{}] Timer completed.", new SimpleDateFormat("HH:mm:ss").format(new Date()), TIMER_NAME);
    }

    /**
     * Prepare dto which will be sent to otomoto REST API
     * and run loop searching for new cars.
     * <p>
     * Loop is finished when there are no more new cars
     * (objects from API are repeating with objects inside database)
     *
     * @param filter currently processed filter
     * @param query final GraphQL indicating String query
     */
    private void loadDataForSingleFilter(Filter filter, String query) {
        OtomotoDto otomotoDto;
        try {
            otomotoDto = OBJECT_MAPPER.readValue(filter.getData(), OtomotoDto.class);
            otomotoDto.setQuery(query);
        } catch (JsonProcessingException e) {
            LOGGER.error("[{}][FilterId: {}] There was an error while parsing data.", TIMER_NAME, filter.getId(), e);
            return;
        }

        if (otomotoDto.getVariables() == null) {
            LOGGER.error("[{}][FilterId: {}] Filter has incorrect json data.", TIMER_NAME, filter.getId());
            return;
        }

        long page = 1;
        boolean found = false;
        while (!found) {
            found = finishedProcessingFilterBatchly(otomotoDto, filter, page);
            page++;
        }
    }

    /**
     * Create dto with next pages and send POST request
     *
     * @param otomotoDto - dto sent to REST API
     * @param filter currently processed filter
     * @param page page number. There are 32 elements per page
     * @return true if there are no more new elements and timer for certain filter should be finished
     */
    private boolean finishedProcessingFilterBatchly(OtomotoDto otomotoDto, Filter filter, Long page) {
        try {
            otomotoDto.getVariables().setPage(page);

            OtomotoResponseDto otomotoResponseDto = getPostResponse(OBJECT_MAPPER.writeValueAsString(otomotoDto));
            return processSingleFilter(filter, otomotoResponseDto);

        } catch (JsonProcessingException e) {
            LOGGER.error("[{}] ERROR GENERATING REQUEST!", TIMER_NAME);
            return true;
        }
    }

    /**
     * Send POST request to the destination path
     *
     * @param jsonData json combining filter data specified by user
     * @return OtomotoResponseDto containing car data
     */
    private OtomotoResponseDto getPostResponse(String jsonData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(destinationPath, requestEntity, String.class);

        LOGGER.debug("Response code: {}", responseEntity.getStatusCode());

        try {
            return OBJECT_MAPPER.readValue(responseEntity.getBody(), OtomotoResponseDto.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("[{}] There was an error while parsing data.", TIMER_NAME, e);
            return new OtomotoResponseDto();
        }
    }

    /**
     * Calculate current filter.
     * If there are new cars, then they should be saved inside the database.
     * Otherwise, filter is being ignored.
     *
     * @param filter currently processed filter
     * @param otomotoResponseDto response from REST API
     * @return true if the process should stop and there won't be any more cars
     */
    private boolean processSingleFilter(Filter filter, OtomotoResponseDto otomotoResponseDto) {
        Set<NodeParentDto> newCars = Optional.ofNullable(otomotoResponseDto)
                .map(OtomotoResponseDto::getData)
                .map(DataDto::getAdvertSearch)
                .map(AdvertSearchDto::getEdges)
                .orElse(new HashSet<>());

        if (Collections.isNullOrEmpty(newCars))
            return true;

        filterService.fetchCars(filter);
        Set<Long> newCarsIds = newCars.stream().map(NodeParentDto::getNode).map(NodeDto::getId).collect(Collectors.toSet());
        Set<Long> oldCarsIds = Collections.isNullOrEmpty(filter.getFilterToCars()) ? new HashSet<>() : filter.getFilterToCars().stream().map(FilterToCar::getCarId).collect(Collectors.toSet());

        // Check if latestAdId already exists in database
        if (latestCarIdExists(otomotoResponseDto, oldCarsIds))
            return true;

        Set<Long> diff = newCarsIds.stream().filter(newCarElement -> !oldCarsIds.contains(newCarElement)).collect(Collectors.toSet());
        LOGGER.info("[{}][FilterId: {}] Found {} new cars: {}", TIMER_NAME, filter.getId(), diff.size(), Collections.toString(diff));

        //Save all new cars into the database
        saveNewCarsToDatabase(diff, filter, filter.getFilterToCars().isEmpty());

        return diff.size() != PAGE_SIZE && !diff.isEmpty();
    }

    /**
     * Check if newest added car exists inside provided Set
     *
     * @param otomotoResponseDto POST response object containing latestAdId
     * @param carIds Set of car ids
     * @return true if Set contains  car id inside DTO object
     */
    private boolean latestCarIdExists(OtomotoResponseDto otomotoResponseDto, Set<Long> carIds) {
        return carIds.stream().anyMatch((id) -> Optional.ofNullable(otomotoResponseDto)
                .map(OtomotoResponseDto::getData)
                .map(DataDto::getAdvertSearch)
                .map(AdvertSearchDto::getLatestAdId)
                .map(id::equals).orElse(false));
    }

    /**
     * Insert car's ids into the database.
     *
     * @param carIds Set of car's ids
     * @param filter currently processed filter
     * @param firstInitialization true if this is the first ever loop for the filter
     */
    private void saveNewCarsToDatabase(Set<Long> carIds, Filter filter, boolean firstInitialization) {
        Set<FilterToCar> filterToCarSet = carIds.stream().map((carId) ->
                FilterToCar.of(filter, carId, firstInitialization ? CarStatus.FIRST_INITIALIZATION : CarStatus.NEW))
                .collect(Collectors.toSet());

        filterService.addCarsToFilter(filterToCarSet);
    }

}
