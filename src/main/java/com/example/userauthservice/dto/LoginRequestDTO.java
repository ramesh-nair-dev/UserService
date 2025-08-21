package com.example.userauthservice.dto;

import com.example.userauthservice.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;

    public static User toUser(LoginRequestDTO loginRequestDTO) {
        User user = new User();
        user.setEmail(loginRequestDTO.getEmail());
        user.setPassword(loginRequestDTO.getPassword());
        return user;
    }
}
