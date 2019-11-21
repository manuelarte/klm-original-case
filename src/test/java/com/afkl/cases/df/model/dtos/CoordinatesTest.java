package com.afkl.cases.df.model.dtos;

import com.afkl.cases.df.config.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {

    private static final ObjectMapper OBJECT_MAPPER = new AppConfig().objectMapper();

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Coordinates.class);
    }

    @Test
    public void testDeserialize() throws IOException {
        final var json = "{\n" +
                "  \"latitude\": 45.32083,\n" +
                "  \"longitude\": -75.67278\n" +
                "}";
        final var actual = OBJECT_MAPPER.readValue(json, Coordinates.class);
        assertEquals(45.32083, actual.getLatitude(), 0.000001);
        assertEquals(-75.67278, actual.getLongitude(), 0.000001);
    }

}