package com.example.userauthservice.repository;

import com.example.userauthservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByTokenValAndExpirationDateAfter(String tokenVal, Date expirationDate);
}
