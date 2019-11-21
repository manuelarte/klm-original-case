package com.afkl.cases.df.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:travel-server.properties")
@ConfigurationProperties(prefix = "urls")
@lombok.Data
public class TravelServerProperties {

    private String token;
    private String airports;
    private String fares;

    public String getAirports() {
        return airports;
    }

    public void setAirports(final String airports) {
        this.airports = airports;
    }

    public String getFares() {
        return fares;
    }

    public void setFares(final String fares) {
        this.fares = fares;
    }

}
