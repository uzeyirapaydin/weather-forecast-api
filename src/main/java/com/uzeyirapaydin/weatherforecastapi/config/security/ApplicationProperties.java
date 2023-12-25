package com.uzeyirapaydin.weatherforecastapi.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationProperties {

    @Value("${jwt.secretKey}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;
}
