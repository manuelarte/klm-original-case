package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Coordinates.CoordinatesBuilder.class)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public final class Coordinates {

    private final double latitude, longitude;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class CoordinatesBuilder {
    }

}
