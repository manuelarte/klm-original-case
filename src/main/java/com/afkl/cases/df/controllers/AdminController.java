package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.StatisticsDto;
import com.afkl.cases.df.model.entities.MetricRequest;
import com.afkl.cases.df.services.MetricRequestService;
import com.afkl.cases.df.transformer.MetricRequestsToStatisticsDtoTransformer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/admin/statistics")
@lombok.AllArgsConstructor
public class AdminController {

    private final MetricRequestService metricRequestService;
    private final MetricRequestsToStatisticsDtoTransformer metricRequestsToStatisticsDtoTransformer;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDto> getStatistics() {
        final List<MetricRequest> collect = StreamSupport.stream(metricRequestService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(metricRequestsToStatisticsDtoTransformer.apply(collect));
    }

    @GetMapping(value = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MetricRequest>> getMetricRequests() {
        return ResponseEntity.ok(metricRequestService.findAll());
    }

}
