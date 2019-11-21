package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotNull;

@JsonDeserialize(builder = PageRequest.PageRequestBuilder.class)
@lombok.AllArgsConstructor
@lombok.Data
@lombok.Builder(toBuilder = true)
public class PageRequest {

    @NotNull
    private final PageInfo page;

    @JsonProperty("_embedded")
    private final Embedded embedded;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PageRequestBuilder {
    }

}
