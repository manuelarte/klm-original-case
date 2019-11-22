package com.afkl.cases.df.services.impl;

import com.afkl.cases.df.config.TravelServerProperties;
import com.afkl.cases.df.config.UniqueIdThreadLocal;
import com.afkl.cases.df.exceptions.ServerException;
import com.afkl.cases.df.exceptions.ValidationException;
import com.afkl.cases.df.model.dtos.FareResponse;
import com.afkl.cases.df.model.dtos.Location;
import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.TravelService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Optional;

@Service
@lombok.AllArgsConstructor
@lombok.extern.slf4j.Slf4j
class TravelServiceImpl implements TravelService {

    private TravelServerProperties travelServerProperties;
    private RestTemplate restTemplate;

    @Override
    public PageRequest getAirports(final Integer page) {
        try {
            // FIX ME. I can't make work the mock travel server with the params terms and page
            final URIBuilder uriBuilder = new URIBuilder(travelServerProperties.getAirports());
            Optional.ofNullable(page).ifPresent(it -> uriBuilder.setParameter("page", String.valueOf(it)));

            return restTemplate.getForEntity(uriBuilder.build(), PageRequest.class).getBody();
        } catch (URISyntaxException e) {
            throw new ServerException("Can't form the airport url", e);
        }
    }

    @Override
    public Location getAirport(final String code) {
        ResponseEntity<Location> response;
        try {
            response = restTemplate.getForEntity(travelServerProperties.getAirport(code), Location.class);
        } catch (final RestClientException e) {
            if (e instanceof HttpClientErrorException) {
                final var casted = (HttpClientErrorException)e;
                final String errorMessage = String.format("Server returned %s. For airport code: %s", casted.getRawStatusCode(), code);
                if (casted.getStatusCode().is4xxClientError()) {
                    throw new ValidationException(errorMessage, "Airport", e);
                }
                throw new ServerException(errorMessage, "Airport", e);
            }
            throw new ServerException("Server error", e);
        }
        return response.getBody();

    }

    @Override
    public FareResponse getFare(final String origin, final String destination) {
        ResponseEntity<FareResponse> response = null;
        try {
            response = restTemplate.getForEntity(travelServerProperties.getFares(origin, destination), FareResponse.class);
        } catch (final RestClientException e) {
            if (e instanceof HttpClientErrorException) {
                final var casted = (HttpClientErrorException)e;
                final String errorMessage = String.format("Server returned %s. For fare for: %s -> %s", casted.getRawStatusCode(), origin, destination);
                if (casted.getStatusCode().is4xxClientError()) {
                    throw new ValidationException(errorMessage, "Fare", e);
                }
                throw new ServerException(errorMessage, "Fare", e);
            }
            throw new ServerException("Server error", e);
        }
        return response.getBody();
    }

}
