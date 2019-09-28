package com.app.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:security.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class JwtSettingsConfiguration {
    private Integer tokenExpirationTime;
    private String tokenIssuer;
    private String tokenSigningKey;
    private String header;
}
