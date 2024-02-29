package com.example.backend.timer.otomoto.response;

import com.example.backend.timer.otomoto.request.FilterValueDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.SecondaryTable;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertSearchDto {

    @JsonProperty("appliedFilters")
    private Set<FilterValueDto> appliedFilters;

    @JsonProperty("edges")
    private Set<NodeParentDto> edges;

    @JsonProperty("latestAdId")
    private Long latestAdId;

    @JsonProperty("totalCount")
    private Long totalCount;

    public Set<FilterValueDto> getAppliedFilters() {
        return appliedFilters;
    }

    public void setAppliedFilters(Set<FilterValueDto> appliedFilters) {
        this.appliedFilters = appliedFilters;
    }

    public Set<NodeParentDto> getEdges() {
        return edges;
    }

    public void setEdges(Set<NodeParentDto> edges) {
        this.edges = edges;
    }

    public Long getLatestAdId() {
        return latestAdId;
    }

    public void setLatestAdId(Long latestAdId) {
        this.latestAdId = latestAdId;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

}
