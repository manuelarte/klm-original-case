package com.afkl.cases.df.model.dtos;

import com.afkl.cases.df.config.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private static final ObjectMapper OBJECT_MAPPER = new AppConfig().objectMapper();

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Location.class);
    }

    @Test
    public void testDeserialize() throws IOException {
        final var json = "{\n" +
                "    \"code\": \"YOW\",\n" +
                "    \"name\": \"Ottawa International\",\n" +
                "    \"description\": \"Ottawa - Ottawa International (YOW), Canada\",\n" +
                "    \"coordinates\": {\n" +
                "        \"latitude\": 45.32083,\n" +
                "        \"longitude\": -75.67278\n" +
                "    },\n" +
                "    \"parent\": {\n" +
                "        \"code\": \"YOW\",\n" +
                "        \"name\": \"Ottawa\",\n" +
                "        \"description\": \"Ottawa (YOW)\",\n" +
                "        \"coordinates\": {\n" +
                "            \"latitude\": 45.33222,\n" +
                "            \"longitude\": -75.68194\n" +
                "        },\n" +
                "        \"parent\": {\n" +
                "            \"code\": \"CA\",\n" +
                "            \"name\": \"Canada\",\n" +
                "            \"description\": \"Canada (CA)\",\n" +
                "            \"coordinates\": {\n" +
                "                \"latitude\": 55.0,\n" +
                "                \"longitude\": -104.0\n" +
                "            },\n" +
                "            \"parent\": {\n" +
                "                \"code\": \"CA\",\n" +
                "                \"name\": \"Canada\",\n" +
                "                \"description\": \"Canada (CA)\",\n" +
                "                \"coordinates\": {\n" +
                "                    \"latitude\": 55.0,\n" +
                "                    \"longitude\": -104.0\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        final var actual = OBJECT_MAPPER.readValue(json, Location.class);
        assertEquals("YOW", actual.getCode());
        assertEquals("Ottawa International", actual.getName());
    }

}