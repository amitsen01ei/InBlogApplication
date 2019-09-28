package com.app.services.impl;

import com.app.configurations.JwtSettingsConfiguration;
import com.app.dtos.security.AuthenticationRequest;
import com.app.dtos.security.JwtToken;
import com.app.dtos.security.TokenDTO;
import com.app.models.User;
import com.app.models.redis.Token;
import com.app.models.security.AccessJwtToken;
import com.app.services.JwtTokenService;
import com.app.services.redis.TokenService;
import com.app.utils.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtSettingsConfiguration settings;
    private final TokenService tokenService;

    @Autowired
    public JwtTokenServiceImpl(JwtSettingsConfiguration settings, TokenService tokenService) {
        this.settings = settings;
        this.tokenService = tokenService;
    }

    @Override
    public TokenDTO getTokens(AuthenticationRequest requestDto, User user) {
        UserContext userContext = getUserContext(user);
        JwtToken accessToken = createAccessJwtToken(userContext);

        var token = Token.builder()
                .jwtToken(accessToken.getToken())
                .expiredAfter(settings.getTokenExpirationTime())
                .createdAt(new Date())
                .userId(user.getId())
                .id(accessToken.getToken())
                .build();

        tokenService.save(token);

        var tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(accessToken);
        return tokenDTO;
    }

    @Override
    public Map<String, String> getUserDataFromToken(String token) {
        var userDataMap = new HashMap<String,String>();
        Claims claims = Jwts.parser().setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token)
                .getBody();

        userDataMap.put("USER_NAME", claims.getSubject());
        userDataMap.put("USER_ID", claims.getId());

        return userDataMap;
    }

    private UserContext getUserContext(User user) {
        return UserContext.create(user.getUsername(), user.getId());
    }

    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");


        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.setId(String.valueOf(userContext.getUserId()));
        var currentTime = ZonedDateTime.now();

        log.debug("Access token claims info. claims={}", claims.toString());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(settings.getTokenExpirationTime())
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
