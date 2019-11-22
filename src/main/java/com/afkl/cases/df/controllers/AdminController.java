package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.Fare;
import com.afkl.cases.df.model.entities.MetricRequest;
import com.afkl.cases.df.services.MetricRequestService;
import com.afkl.cases.df.services.TravelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/admin/statistics")
@lombok.AllArgsConstructor
public class AdminController {

    private final MetricRequestService metricRequestService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<MetricRequest>> getMetricRequests() {
        return ResponseEntity.ok(metricRequestService.findAll());
    }

}
