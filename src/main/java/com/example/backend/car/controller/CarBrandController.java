package com.example.backend.car.controller;

import com.example.backend.car.dto.CarBrandDto;
import com.example.backend.car.service.CarBrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarBrandController {

    private final CarBrandService carBrandService;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @GetMapping("list")
    public List<CarBrandDto> get() {
        return carBrandService.list().stream().map((carBrand) -> CarBrandDto.of(carBrand.getId(), carBrand.getName())).toList();
    }

}
