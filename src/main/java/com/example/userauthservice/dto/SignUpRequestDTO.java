package com.example.userauthservice.dto;

import com.example.userauthservice.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public static User toUser(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setUsername(signUpRequestDTO.getUsername());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPassword(signUpRequestDTO.getPassword());
        user.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        return user;
    }
}
