package com.contactkeeper.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    @Autowired
    private JwtConfig jwtConfig;

    @Bean
    public SecretKey getSecretKeyForSigning() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
