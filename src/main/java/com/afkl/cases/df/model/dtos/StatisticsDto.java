package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;

@JsonDeserialize(builder = StatisticsDto.StatisticsDtoBuilder.class)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class StatisticsDto {

    private final Long totalNumberRequestsProcessed;
    private final Long totalNumberRequest2xx;
    private final Long totalNumberRequest4xx;
    private final Long totalNumberRequest5xx;
    private final BigDecimal averageResponse;
    private final BigDecimal minResponse;
    private final BigDecimal maxResponse;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class StatisticsDtoBuilder {
    }

}
