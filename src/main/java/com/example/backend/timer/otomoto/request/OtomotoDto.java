package com.example.backend.timer.otomoto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtomotoDto {

    private String query;

    private VariablesDto variables;

    public static OtomotoDto of(String query) {
        OtomotoDto dto = new OtomotoDto();
        dto.setQuery(query);
        return dto;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public VariablesDto getVariables() {
        return variables;
    }

    public void setVariables(VariablesDto variables) {
        this.variables = variables;
    }
}
