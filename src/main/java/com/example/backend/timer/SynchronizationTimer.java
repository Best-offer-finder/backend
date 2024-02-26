package com.example.backend.timer;

import com.example.backend.common.Collections;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.filter.model.Filter;
import com.example.backend.setting.Key;
import com.example.backend.setting.service.ApplicationSettingService;
import com.example.backend.timer.otomoto.OtomotoTimer;
import com.example.backend.timer.otomoto.dto.OtomotoDto;
import com.example.backend.user.model.User;
import com.example.backend.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SynchronizationTimer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationTimer.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final OtomotoTimer otomotoTimer;

    private final ApplicationSettingService applicationSettingService;
    private final UserService userService;

    public SynchronizationTimer(OtomotoTimer otomotoTimer, ApplicationSettingService applicationSettingService, UserService userService) {
        this.otomotoTimer = otomotoTimer;
        this.applicationSettingService = applicationSettingService;
        this.userService = userService;
    }

    @Scheduled(fixedRate = 30 * 1000)
    public void runSynchronizationTimers() throws EntityNotFoundException {
        List<User> allUsers = userService.getAll();
        String query = applicationSettingService.getString(Key.OTOMOTO_QUERY_STRING);

        try {
            query = OBJECT_MAPPER.readValue(query, OtomotoDto.class).getQuery();
        } catch (JsonProcessingException e) {
            LOGGER.error("[Timer] Invalid query: {}", query);
        }


        for (User user : allUsers) {
            runOtomotoSynchronizationForUser(user, query);
        }

        LOGGER.info("The time is now {}", new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    private void runOtomotoSynchronizationForUser(User user, String query) {
        List<Filter> filters = user.getFilters();

        if (Collections.isNullOrEmpty(filters))
            return;

        for (Filter filter : filters) {
            try {
                OtomotoDto dto = OBJECT_MAPPER.readValue(filter.getData(), OtomotoDto.class);
                dto.setQuery(query);

                otomotoTimer.getOtomotoSearch(OBJECT_MAPPER.writeValueAsString(dto));

            } catch (JsonProcessingException e) {
                LOGGER.error("[Timer][{}] There was an error while parsing data.", filter.getId(), e);
            }
        }
    }
}
