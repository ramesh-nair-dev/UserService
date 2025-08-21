package com.example.userauthservice.exceptions;

public class PasswordMisMatchException extends Exception {
    public PasswordMisMatchException(String message) {
        super(message);
    }

    // This exception is thrown when the password provided does not match the expected password.
    // It extends the Exception class and takes a message as a parameter.
    // The message can be used to provide more context about the error.
    // This exception can be caught and handled in the UserAuthServiceExceptionHandler class.
}
