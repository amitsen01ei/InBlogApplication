package com.app.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
@Slf4j
public class RedisConfiguration {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis () throws IOException {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
        log.debug("Redis has started");
    }

    @PreDestroy
    public void stopRedis () {
        redisServer.stop();
        log.debug("Redis has stopped");
    }
}
