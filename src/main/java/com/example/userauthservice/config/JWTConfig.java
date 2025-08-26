package com.example.userauthservice.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JWTConfig {

    @Value("${jwt.secret}")
    String SECRET_KEY_STRING;
    @Bean
    public SecretKey getSecretKey() {

         // Replace with your actual secret key
        return Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    }
}
