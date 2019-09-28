package com.app.services.redis.impl;

import com.app.models.redis.Token;
import com.app.repositories.redis.TokenRepository;
import com.app.services.redis.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findByUserId (String userId) {
        return tokenRepository.findByUserId(userId);
    }

    @Override
    public void deleteByUserId(String userId) {
        tokenRepository.deleteByUserId(userId);
    }
}
