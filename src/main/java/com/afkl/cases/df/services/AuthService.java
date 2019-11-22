package com.afkl.cases.df.services;

import com.afkl.cases.df.model.dtos.AuthTokenResponse;

public interface AuthService {

    AuthTokenResponse getAccessToken(String clientId, String password);
}
