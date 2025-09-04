package com.example.userauthservice.service;
import com.example.userauthservice.exceptions.InvalidTokenException;
import com.example.userauthservice.model.Token;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface AuthService {
    User signUp(User user) throws UserAlreadyExistsException, JsonProcessingException;
    Token login(User user) throws UserNotExistsException, PasswordMisMatchException;
    User validateToken(String token) throws InvalidTokenException, UserNotExistsException;
}
