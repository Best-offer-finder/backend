package com.example.backend.filter.repository;

import com.example.backend.filter.model.FilterToCar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface FilterToCarRepository extends JpaRepository<FilterToCar, Long> {

    Set<FilterToCar> findAllByFilterId(Long filterId);

}
