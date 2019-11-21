package com.afkl.cases.df.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@lombok.AllArgsConstructor
public class AppConfig {

    private final RestAuthenticationInterceptor restAuthenticationInterceptor;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules();
    }

    @Bean
    public RestTemplate restController() {
        final var restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(restAuthenticationInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
