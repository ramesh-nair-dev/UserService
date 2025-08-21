package com.example.userauthservice.dto;

import com.example.userauthservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SignUpResponseDTO {
    private long userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String message;
    private ResponseStatus responseStatus;
    private List<String> roles;

    public static SignUpResponseDTO fromUser(User user){
        SignUpResponseDTO response = new SignUpResponseDTO();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRoles(user.getRoleList().stream().map(role -> role.getName()).collect(Collectors.toList()));
        response.setMessage("User registered successfully");
        response.setResponseStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
