package com.example.userauthservice.dto;

import com.example.userauthservice.model.Token;
import com.example.userauthservice.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private long userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String message;
    private String token;
    private ResponseStatus responseStatus;

    public static LoginResponseDTO fromUser(User user , Token token){
        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setToken(token.getTokenVal());
        response.setMessage("Login successful");
        response.setResponseStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
