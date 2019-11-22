package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.Fare;
import com.afkl.cases.df.services.TravelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping("/fares")
@lombok.AllArgsConstructor
public class FareController {

    private final TravelService travelService;

    @GetMapping(value = "/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Fare getFare(@PathVariable final String origin, @PathVariable final String destination) {
        Fare.FareBuilder builder = Fare.builder();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final var originCf = CompletableFuture.supplyAsync(() -> travelService.getAirport(origin), executorService).thenAcceptAsync(it -> builder.origin(it));
        final var destinationCf = CompletableFuture.supplyAsync(() -> travelService.getAirport(destination), executorService).thenAcceptAsync(it -> builder.destination(it));
        final var fareCf = CompletableFuture.supplyAsync(() -> travelService.getFare(origin, destination), executorService).thenAcceptAsync(it -> {
            builder.amount(it.getAmount());
            builder.currency(it.getCurrency());
        });

        CompletableFuture.allOf(originCf, destinationCf, fareCf).join();
        return builder.build();
    }

}
