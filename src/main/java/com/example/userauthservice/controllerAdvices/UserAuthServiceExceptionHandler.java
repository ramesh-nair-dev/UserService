package com.example.userauthservice.controllerAdvices;

import com.example.userauthservice.dto.ResponseStatus;
import com.example.userauthservice.dto.exception.ExceptionDTO;
import com.example.userauthservice.exceptions.PasswordMisMatchException;
import com.example.userauthservice.exceptions.UserAlreadyExistsException;
import com.example.userauthservice.exceptions.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAuthServiceExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(exception.getMessage());
        exceptionDTO.setResponseStatus(ResponseStatus.FAILURE);
        exceptionDTO.setResolutionDetails("Please try again later or contact support if the issue persists.");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exceptionDTO);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleUserAlreadyExistsException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(exception.getMessage());
        exceptionDTO.setResponseStatus(ResponseStatus.FAILURE);
        exceptionDTO.setResolutionDetails("The user already exists. Please try with a different username or email.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionDTO);
    }

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotExistsException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(exception.getMessage());
        exceptionDTO.setResponseStatus(ResponseStatus.FAILURE);
        exceptionDTO.setResolutionDetails("The user does not exist. Please check the username or email and try again otherwise register.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(PasswordMisMatchException.class)
    public ResponseEntity<ExceptionDTO> handlePasswordMisMatchException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(exception.getMessage());
        exceptionDTO.setResponseStatus(ResponseStatus.FAILURE);
        exceptionDTO.setResolutionDetails("The password does not match. Please check the password and try again.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDTO);
    }

}
