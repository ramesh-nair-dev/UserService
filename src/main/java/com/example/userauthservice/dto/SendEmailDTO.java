package com.example.userauthservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO {
    private String to;
    private String subject;
    private String body;

    public static SendEmailDTO from(String email ,String name) {
        SendEmailDTO dto = new SendEmailDTO();
        dto.setTo(email);
        dto.setSubject("Welcome " +name+" to User Auth Service");
        dto.setBody("Thank you for registering with User Auth Service.");
        return dto;
    }
}
