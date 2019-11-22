package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = PageInfo.PageInfoBuilder.class)
@lombok.AllArgsConstructor
@lombok.Data
@lombok.Builder(toBuilder = true)
public class PageInfo {

    private final int size;
    private final int totalElements;
    private final int totalPages;
    private final int number;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PageInfoBuilder {
    }

}
