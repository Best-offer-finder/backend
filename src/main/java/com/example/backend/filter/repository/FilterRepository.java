package com.example.backend.filter.repository;

import com.example.backend.filter.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Optional<Filter> findFilterById(Long id);

}
