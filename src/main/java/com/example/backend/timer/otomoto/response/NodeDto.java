package com.example.backend.timer.otomoto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeDto {

    @JsonProperty("id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("location")
    private LocationDto location;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("parameters")
    private Set<ParameterDto> parameters;

    @JsonProperty("price")
    private PriceDto price;

    @JsonProperty("thumbnail")
    private ThumbnailDto thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Set<ParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(Set<ParameterDto> parameters) {
        this.parameters = parameters;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setPrice(PriceDto price) {
        this.price = price;
    }

    public ThumbnailDto getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailDto thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
