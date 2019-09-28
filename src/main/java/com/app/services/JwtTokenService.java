package com.app.services;

import com.app.dtos.security.AuthenticationRequest;
import com.app.dtos.security.TokenDTO;
import com.app.models.User;

import java.util.Map;

public interface JwtTokenService {
    TokenDTO getTokens(AuthenticationRequest requestDto, User user);

    Map<String,String> getUserDataFromToken (String token);
}
