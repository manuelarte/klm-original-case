package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = FareResponse.FareResponseBuilder.class)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class FareResponse {

    private final double amount;
    private final Currency currency;
    private final String origin, destination;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class FareResponseBuilder {
    }

}
