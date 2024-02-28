package com.example.backend.timer.otomoto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDto {

    @JsonProperty("advertSearch")
    private AdvertSearchDto advertSearch;

    public AdvertSearchDto getAdvertSearch() {
        return advertSearch;
    }

    public void setAdvertSearch(AdvertSearchDto advertSearch) {
        this.advertSearch = advertSearch;
    }

}
