package com.afkl.cases.df.config;

import lombok.SneakyThrows;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;

@Configuration
@PropertySource("classpath:travel-server.properties")
@ConfigurationProperties(prefix = "urls")
@lombok.Data
public class TravelServerProperties {

    private String token;

    @URL
    private String airports;

    @URL
    private String fares;

    @SneakyThrows
    public URI getAirports() {
        return new URI(airports);
    }

    @SneakyThrows
    public URI getAirport(final String code) {
        return new URI(airports + "/" + code);
    }

    public void setAirports(final String airports) {
        this.airports = airports;
    }

    @SneakyThrows
    public URI getFares(final String origin, final String destination) {
        return new URI(fares + String.format("/%s/%s", origin, destination) );
    }

    public void setFares(final String fares) {
        this.fares = fares;
    }

}
