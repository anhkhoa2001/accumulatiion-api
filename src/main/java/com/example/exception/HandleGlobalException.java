package com.example.exception;

import com.example.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Date;

@ControllerAdvice
public class HandleGlobalException {

    @ExceptionHandler({SystemException.class})
    public final ResponseEntity<Object> handleAllException(SystemException ex, ServletWebRequest request) {
        ExceptionResponse msg = new ExceptionResponse();
        msg.setTimestamp(new Date().getTime());
        msg.setPath(request.getRequest().getRequestURI());
        msg.setMessage(ex.getMessage());

        return new ResponseEntity<>(msg, ex.getCode());
    }
}
