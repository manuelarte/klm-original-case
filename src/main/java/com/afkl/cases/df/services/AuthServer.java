package com.afkl.cases.df.services;

import com.afkl.cases.df.model.dtos.AuthTokenResponse;

public interface AuthServer {

    AuthTokenResponse getAccessToken(String clientId, String password);
}
