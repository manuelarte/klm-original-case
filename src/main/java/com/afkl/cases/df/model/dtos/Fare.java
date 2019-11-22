package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Fare.FareBuilder.class)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class Fare {

    private double amount;
    private Currency currency;
    private Location origin, destination;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class FareBuilder {
    }

}
