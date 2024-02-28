package com.example.backend.filter.service;

import com.example.backend.common.ErrorCode;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.filter.model.Filter;
import com.example.backend.filter.model.FilterToCar;
import com.example.backend.filter.repository.FilterRepository;
import com.example.backend.filter.repository.FilterToCarRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterService {

    private final EntityManager em;
    private final FilterRepository filterRepository;
    private final FilterToCarRepository filterToCarRepository;

    public FilterService(EntityManager em, FilterRepository filterRepository, FilterToCarRepository filterToCarRepository) {
        this.em = em;
        this.filterRepository = filterRepository;
        this.filterToCarRepository = filterToCarRepository;
    }

    public Filter getById(Long id) throws EntityNotFoundException {
        return filterRepository.findFilterById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.FILTER_NOT_FOUND));
    }

    public void fetchCars(Filter filter) {
        Set<FilterToCar> filterToCars = filterToCarRepository.findAllByFilterId(filter.getId());

        em.detach(filter);
        filter.setFilterToCars(filterToCars);
    }

    public void addCarsToFilter(Set<FilterToCar> filterToCarSet) {
        filterToCarRepository.saveAll(filterToCarSet);
    }

}
