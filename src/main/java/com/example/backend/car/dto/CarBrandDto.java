package com.example.backend.car.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarBrandDto {

    private Integer id;

    private String name;

    public static CarBrandDto of(Integer id, String name) {
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setId(id);
        carBrandDto.setName(name);
        return carBrandDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
