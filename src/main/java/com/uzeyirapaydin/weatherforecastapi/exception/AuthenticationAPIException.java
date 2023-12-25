package com.uzeyirapaydin.weatherforecastapi.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public AuthenticationAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public AuthenticationAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
