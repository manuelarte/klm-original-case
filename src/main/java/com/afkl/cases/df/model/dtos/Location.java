package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonDeserialize(builder = Location.LocationBuilder.class)
@JsonInclude(NON_NULL)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public final class Location {

    private final String code, name, description;
    private final Coordinates coordinates;
    private final Location parent;
    private final Set<Location> children;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class LocationBuilder {
    }

}
