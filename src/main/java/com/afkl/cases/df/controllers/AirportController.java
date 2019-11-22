package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.Location;
import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.TravelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/airports")
@lombok.AllArgsConstructor
public class AirportController {

    private final TravelService travelService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageRequest getAirports(@RequestParam(value = "page", required = false, defaultValue = "0") final Integer page) {
        return travelService.getAirports(page);
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Location getAirport(@PathVariable final String code) {
        return travelService.getAirport(code);
    }

}
