package com.app.repositories.redis;

import com.app.models.redis.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    Optional<Token> findByUserId (String userId);
    void deleteByUserId (String userId);
}
