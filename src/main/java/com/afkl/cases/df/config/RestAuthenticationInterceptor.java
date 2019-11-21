package com.afkl.cases.df.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@lombok.AllArgsConstructor
public class RestAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private final AccessTokenProvider accessTokenProvider;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final var headers = request.getHeaders();
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            headers.put(HttpHeaders.AUTHORIZATION, Collections.singletonList("Bearer " + accessTokenProvider.getAccessToken()));
        }
        return execution.execute(request, body);
    }

}
