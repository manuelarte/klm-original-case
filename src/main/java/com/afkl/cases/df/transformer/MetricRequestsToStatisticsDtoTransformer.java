package com.afkl.cases.df.transformer;

import com.afkl.cases.df.model.dtos.StatisticsDto;
import com.afkl.cases.df.model.entities.MetricRequest;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
public class MetricRequestsToStatisticsDtoTransformer implements Function<List<MetricRequest>, StatisticsDto> {

    @Override
    public StatisticsDto apply(final List<MetricRequest> metrics) {
        return StatisticsDto.builder().totalNumberRequestsProcessed(Long.valueOf(metrics.size()))
                .totalNumberRequest2xx(metrics.stream().filter(codeBetween(200, 299)).count())
                .totalNumberRequest4xx(metrics.stream().filter(codeBetween(400, 499)).count())
                .totalNumberRequest5xx(metrics.stream().filter(codeBetween(400, 599)).count())
                .averageResponse(convert(metrics.stream().mapToLong(MetricRequest::getTime).average().orElse(0d)))
                .maxResponse(convert(metrics.stream().mapToLong(MetricRequest::getTime).max().orElse(0L)))
                .minResponse(convert(metrics.stream().mapToLong(MetricRequest::getTime).min().orElse(0L)))
                .build();
    }

    private Predicate<MetricRequest> codeBetween(final int start, final int end) {
        return m -> m.getCode() >= start && m.getCode() <= end;
    }

    private BigDecimal convert(final double value) {
        return new BigDecimal(String.valueOf(value)).setScale(2, RoundingMode.HALF_EVEN);
    }

}
