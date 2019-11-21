package com.afkl.cases.df.services.impl;

import com.afkl.cases.df.config.TravelServerProperties;
import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.TravelServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
@lombok.AllArgsConstructor
public class TravelServerImpl implements TravelServer {

    private TravelServerProperties travelServerProperties;
    private RestTemplate restTemplate;

    @Override
    public PageRequest getAirports() {
        return restTemplate.getForEntity(travelServerProperties.getAirports(), PageRequest.class).getBody();
    }

}
