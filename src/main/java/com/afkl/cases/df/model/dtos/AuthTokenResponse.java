package com.afkl.cases.df.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AuthTokenResponse.AuthTokenResponseBuilder.class)
@lombok.AllArgsConstructor
@lombok.Value
@lombok.Builder(toBuilder = true)
public class AuthTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;

    @JsonProperty("token_type")
    private final String tokenType;

    @JsonProperty("expires_in")
    private final int expiresIn;

    private final String scope;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AuthTokenResponseBuilder {
    }

}
