package com.example.userauthservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailEventDTO {
    private String to;
    private String subject;
    private String body;

    public static SendEmailEventDTO from(String email , String name) {
        SendEmailEventDTO dto = new SendEmailEventDTO();
        dto.setTo(email);
        dto.setSubject("Welcome " +name+" to User Auth Service");
        dto.setBody("Thank you for registering with User Auth Service.");
        return dto;
    }
}
