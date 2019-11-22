package com.afkl.cases.df.services;

import com.afkl.cases.df.model.dtos.FareResponse;
import com.afkl.cases.df.model.dtos.Location;
import com.afkl.cases.df.model.dtos.PageRequest;

public interface TravelService {

    // add query parameter
    PageRequest getAirports(Integer page);

    Location getAirport(String code);

    FareResponse getFare(String origin, String destination);

}
