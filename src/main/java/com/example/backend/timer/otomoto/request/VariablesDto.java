package com.example.backend.timer.otomoto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariablesDto {

    private String click2BuyExperimentId = "CARS-3418";

    private String click2BuyExperimentVariant = "b";

    private Set<FilterValueDto> filters;

    private final boolean includeCepik = false;
    private final boolean includeClick2Buy = false;
    private final boolean includeFiltersCounters = false;
    private final boolean includePriceEvaluation = false;
    private final boolean includePromotedAds = false;
    private final boolean includeRatings = false;
    private final boolean includeSortOptions = false;
    private final boolean includeSuggestedFilters = false;

    private final long maxAge = 1;

    private long page = 1;

    private final List<String> parameters = new ArrayList<>(Arrays.asList("color", "country_origin", "damaged",
            "engine_capacity", "engine_power", "fuel_type", "gearbox", "is_imported_car", "make", "mileage",
            "model", "version", "vin", "year"));

    private final Object searchTerms = null;

    private final String sortBy = "created_at_first:desc";

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

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public Set<FilterValueDto> getFilters() {
        return filters;
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

    public long getMaxAge() {
        return maxAge;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public Object getSearchTerms() {
        return searchTerms;
    }

    public String getSortBy() {
        return sortBy;
    }
}
