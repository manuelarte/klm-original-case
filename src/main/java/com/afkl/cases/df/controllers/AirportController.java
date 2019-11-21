package com.afkl.cases.df.controllers;

import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.TravelServer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/airports")
//@lombok.AllArgsConstructor
public class AirportController {

    private final TravelServer travelServer;

    public AirportController(final TravelServer travelServer) {
        this.travelServer = travelServer;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageRequest> findByPage() {
        return ResponseEntity.ok(travelServer.getAirports());
    }

}
