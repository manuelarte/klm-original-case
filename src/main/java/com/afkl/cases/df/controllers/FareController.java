package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.Fare;
import com.afkl.cases.df.services.TravelService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/users/fares")
@lombok.AllArgsConstructor
@lombok.extern.slf4j.Slf4j
public class FareController {

    private final TravelService travelService;

    @GetMapping(value = "/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Fare getFare(@PathVariable final String origin, @PathVariable final String destination) {
        log.info("Requesting fare for {}-{}", origin, destination);
        Fare.FareBuilder builder = Fare.builder();

        final var namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("async-task-%d").build();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), namedThreadFactory);

        final var originCf = CompletableFuture.supplyAsync(() -> travelService.getAirport(origin), executorService).thenAcceptAsync(builder::origin);
        final var destinationCf = CompletableFuture.supplyAsync(() -> travelService.getAirport(destination), executorService).thenAcceptAsync(builder::destination);
        final var fareCf = CompletableFuture.supplyAsync(() -> travelService.getFare(origin, destination), executorService).thenAcceptAsync(it -> {
            builder.amount(it.getAmount());
            builder.currency(it.getCurrency());
        });

        CompletableFuture.allOf(originCf, destinationCf, fareCf).join();
        return builder.build();
    }

}
