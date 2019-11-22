package com.afkl.cases.df.services;

import com.afkl.cases.df.exceptions.ValidationException;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("it")
@ExtendWith({SpringExtension.class})
class TravelServiceTest {

    @Inject
    private TravelService travelService;

    private static WireMockServer wireMockServer;

    @BeforeEach
    public void beforeEach() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        stubFor(post(urlEqualTo("/oauth/token"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));
    }

    @AfterEach
    public void afterEach() {
        wireMockServer.stop();
    }

    @Test
    public void testGetAirportServerResponse200() {
        final String code = "AGP";
        stubFor(get(urlEqualTo("/airports/"+code))
                //.withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{}")));
        travelService.getAirport(code);
    }

    @Test
    public void testGetAirportServerResponse404() {
        final String code = "AGP";
        stubFor(get(urlEqualTo("/airports/"+code))
                //.withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));
        assertThrows(ValidationException.class, () -> travelService.getAirport(code));
    }

}