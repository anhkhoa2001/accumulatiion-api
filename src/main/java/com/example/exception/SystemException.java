package com.example.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends RuntimeException {
    private HttpStatus code;
    private String message;

    public SystemException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
    public SystemException(String msg){
        super(msg);
    }

    public HttpStatus getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
