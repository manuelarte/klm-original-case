package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = Embedded.EmbeddedBuilder.class)
@lombok.AllArgsConstructor
@lombok.Data
@lombok.Builder(toBuilder = true)
public class Embedded {

    private final List<Location> locations;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class EmbeddedBuilder {
    }

}
