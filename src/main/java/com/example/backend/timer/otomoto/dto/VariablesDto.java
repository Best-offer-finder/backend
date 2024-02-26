package com.example.backend.timer.otomoto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariablesDto {

    private String click2BuyExperimentId = "CARS-3418";

    private String click2BuyExperimentVariant = "b";

    private Set<FilterValueDto> filters;

    private final boolean includeCepik = false;
    private final boolean includeClick2Buy = false;
    private final boolean includeFiltersCounters = false;
    private final boolean includePriceEvaluation = true;
    private final boolean includePromotedAds = false;
    private final boolean includeRatings = false;
    private final boolean includeSortOptions = false;
    private final boolean includeSuggestedFilters = false;

    public String getClick2BuyExperimentId() {
        return click2BuyExperimentId;
    }

    public void setClick2BuyExperimentId(String click2BuyExperimentId) {
        this.click2BuyExperimentId = click2BuyExperimentId;
    }

    public String getClick2BuyExperimentVariant() {
        return click2BuyExperimentVariant;
    }

    public void setClick2BuyExperimentVariant(String click2BuyExperimentVariant) {
        this.click2BuyExperimentVariant = click2BuyExperimentVariant;
    }

    public Set<FilterValueDto> getFilters() {
        return filters;
    }

    public void setFilters(Set<FilterValueDto> filters) {
        this.filters = filters;
    }

    public boolean isIncludeCepik() {
        return includeCepik;
    }

    public boolean isIncludeClick2Buy() {
        return includeClick2Buy;
    }

    public boolean isIncludeFiltersCounters() {
        return includeFiltersCounters;
    }

    public boolean isIncludePriceEvaluation() {
        return includePriceEvaluation;
    }

    public boolean isIncludePromotedAds() {
        return includePromotedAds;
    }

    public boolean isIncludeRatings() {
        return includeRatings;
    }

    public boolean isIncludeSortOptions() {
        return includeSortOptions;
    }

    public boolean isIncludeSuggestedFilters() {
        return includeSuggestedFilters;
    }
}
