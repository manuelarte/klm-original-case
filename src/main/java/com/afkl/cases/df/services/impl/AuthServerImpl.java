package com.afkl.cases.df.services.impl;

import com.afkl.cases.df.config.TravelServerProperties;
import com.afkl.cases.df.model.dtos.AuthTokenResponse;
import com.afkl.cases.df.model.dtos.PageRequest;
import com.afkl.cases.df.services.AuthServer;
import com.afkl.cases.df.services.TravelServer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Base64;

@Service
@lombok.AllArgsConstructor
public class AuthServerImpl implements AuthServer {

    private TravelServerProperties travelServerProperties;

    @Override
    public AuthTokenResponse getAccessToken(final String clientId, final String password) {
        final String url = travelServerProperties.getToken();
        final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");
        final var httpEntity = new HttpEntity(map, createBasicAuthHeaders(clientId, password));
        return new RestTemplate().exchange(url, HttpMethod.POST, httpEntity, AuthTokenResponse.class).getBody();
    }

    private final MultiValueMap<String, String> createBasicAuthHeaders(final String clientId, final String password) {
        final String auth = clientId + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        return headers;
    }
}