package com.example.userauthservice.repository;

import com.example.userauthservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
}
