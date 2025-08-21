package com.example.userauthservice.dto.exception;

import com.example.userauthservice.dto.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private String message;
    private String ResolutionDetails;
    private ResponseStatus responseStatus;

}
