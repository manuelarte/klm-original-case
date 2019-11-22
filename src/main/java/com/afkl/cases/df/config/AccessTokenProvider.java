package com.afkl.cases.df.config;

import com.afkl.cases.df.model.dtos.AuthTokenResponse;
import com.afkl.cases.df.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
class AccessTokenProvider {

    private final AuthService authService;
    private final String clientId;
    private final String password;

    public AccessTokenProvider(final AuthService authService, @Value("${client-id}") final String clientId,
                               @Value("${password}") final String password) {
        this.authService = authService;
        this.clientId = clientId;
        this.password = password;
    }

    private Instant requested;
    private AuthTokenResponse authTokenResponse;

    public String getAccessToken() {
        if (authTokenResponse == null || Instant.now().compareTo(tokenExpireInstant()) > 0) {
            // ask for a new token, set and return
            authTokenResponse = authService.getAccessToken(clientId, password);
            requested = Instant.now();
        }
        return authTokenResponse.getAccessToken();
    }

    private Instant tokenExpireInstant() {
        return requested.plus(authTokenResponse.getExpiresIn(), ChronoUnit.SECONDS);
    }
}
