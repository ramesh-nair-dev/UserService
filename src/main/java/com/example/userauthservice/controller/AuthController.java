package com.example.userauthservice.controller;

import com.example.userauthservice.dto.*;
import com.example.userauthservice.exceptions.InvalidTokenException;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.Token;
import com.example.userauthservice.model.User;
import com.example.userauthservice.service.AuthService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequest) throws UserAlreadyExistsException {
        User user = authService.signUp(SignUpRequestDTO.toUser(signUpRequest));
        return SignUpResponseDTO.fromUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws UserNotExistsException, PasswordMisMatchException {
        Token userToken = authService.login(LoginRequestDTO.toUser(loginRequest));
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponseDTO.fromUser(userToken.getUser(),userToken));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDTO> validateToken(@RequestHeader("token") String token) throws InvalidTokenException, UserNotExistsException {
        // Token validation logic can be implemented here
        // For now, we will just return a success message
        System.out.println("Validating token: " + token);
        User user = authService.validateToken(token);
        return ResponseEntity.status(HttpStatus.OK)
                .body(UserDTO.fromUser(user));
    }


}
