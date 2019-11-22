package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.Location;
import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.TravelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airports")
@lombok.AllArgsConstructor
public class AirportController {

    private final TravelService travelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageRequest getAirports() {
        return travelService.getAirports();
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Location getAirport(@PathVariable final String code) {
        return travelService.getAirport(code);
    }

}
