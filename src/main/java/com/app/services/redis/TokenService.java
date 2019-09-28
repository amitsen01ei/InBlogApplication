package com.app.services.redis;

import com.app.models.redis.Token;

import java.util.Optional;

public interface TokenService  {
    void save(Token token);
    Optional<Token> findByUserId (String userId);
    void deleteByUserId (String userId);
}