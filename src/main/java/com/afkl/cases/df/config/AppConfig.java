package com.afkl.cases.df.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@lombok.AllArgsConstructor
public class AppConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return  new ObjectMapper().findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    public RestTemplate restController(final RestAuthenticationInterceptor restAuthenticationInterceptor) {
        final var restTemplate = new RestTemplate();
        var interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(restAuthenticationInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
