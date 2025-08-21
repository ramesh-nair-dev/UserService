package com.example.userauthservice.service;
import com.example.userauthservice.model.Token;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.User;


public interface AuthService {
    User signUp(User user) throws UserAlreadyExistsException;
    Token login(User user) throws UserNotExistsException, PasswordMisMatchException;
}
