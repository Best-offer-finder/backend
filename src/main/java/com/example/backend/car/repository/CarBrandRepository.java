package com.example.backend.car.repository;

import com.example.backend.car.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {

    List<CarBrand> findAllByOrderByNameAsc();

}
