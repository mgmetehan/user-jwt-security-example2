package com.mgmetehan.userjwtsecurityexample2.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> customGeneralException(GeneralException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        errors.put("status", exception.getCode());
        errors.put("repeatable", exception.getRepeatable().toString());
        return new ResponseEntity<>(errors, exception.getHttpStatus());
    }
}