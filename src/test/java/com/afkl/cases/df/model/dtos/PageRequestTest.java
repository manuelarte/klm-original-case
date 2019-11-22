package com.afkl.cases.df.model.dtos;

import com.afkl.cases.df.config.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PageRequestTest {

    private static final ObjectMapper OBJECT_MAPPER = new AppConfig().objectMapper();

    @Test
    public void testDeserialize() throws IOException, URISyntaxException {
        final var url = PageRequestTest.class.getResource("/example-locations.json");
        final var resPath = Paths.get(url.toURI());
        String json = new String(Files.readAllBytes(resPath), StandardCharsets.UTF_8);

        final var expectedPageInfo = new PageInfo(25, 1048, 42, 1);

        final var actual = OBJECT_MAPPER.readValue(json, PageRequest.class);

        assertEquals(expectedPageInfo, actual.getPage());
        assertEquals(expectedPageInfo.getSize(), actual.getEmbedded().getLocations().size());
        //assertEquals(-75.67278, actual.getLongitude(), 0.000001);
    }
}
