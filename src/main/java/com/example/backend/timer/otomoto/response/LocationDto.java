package com.example.backend.timer.otomoto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    @JsonProperty("city")
    private CityDto city;

    @JsonProperty("region")
    private CityDto region;

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public CityDto getRegion() {
        return region;
    }

    public void setRegion(CityDto region) {
        this.region = region;
    }

}
