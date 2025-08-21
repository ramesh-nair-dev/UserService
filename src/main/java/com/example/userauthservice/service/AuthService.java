package com.example.userauthservice.service;
import org.antlr.v4.runtime.misc.Pair;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.User;


public interface AuthService {
    User signUp(User user) throws UserAlreadyExistsException;
    Pair<User ,String> login(User user) throws UserNotExistsException, PasswordMisMatchException;
}
