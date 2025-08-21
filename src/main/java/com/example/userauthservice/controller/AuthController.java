package com.example.userauthservice.controller;

import com.example.userauthservice.dto.LoginRequestDTO;
import com.example.userauthservice.dto.LoginResponseDTO;
import com.example.userauthservice.dto.SignUpRequestDTO;
import com.example.userauthservice.dto.SignUpResponseDTO;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import com.example.userauthservice.model.User;
import com.example.userauthservice.service.AuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Pair<User , String> userAndToken = authService.login(LoginRequestDTO.toUser(loginRequest));
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponseDTO.fromUser(userAndToken.a));
    }


}
