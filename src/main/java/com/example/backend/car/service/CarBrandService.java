package com.example.backend.car.service;

import com.example.backend.car.model.CarBrand;
import com.example.backend.car.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandService {

    private final CarBrandRepository carBrandRepository;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public List<CarBrand> list() {
        return carBrandRepository.findAllByOrderByNameAsc();
    }

}
