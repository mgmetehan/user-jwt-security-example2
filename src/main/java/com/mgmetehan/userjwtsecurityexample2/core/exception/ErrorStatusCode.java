package com.mgmetehan.userjwtsecurityexample2.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorStatusCode {
    NOT_FOUND(10000, "Not Found", false, HttpStatus.NO_CONTENT),
    UNAUTHORIZED(10001, "Unauthorized", false, HttpStatus.UNAUTHORIZED),
    TOKEN_ALREADY_INVALIDATED(10001, "Token Already Invalidated", false, HttpStatus.INTERNAL_SERVER_ERROR);

    private final int value;
    private final String description;
    private final boolean isRepeatable;
    private final HttpStatus httpStatus;

    public static ErrorStatusCode getByValue(int value) {
        for (ErrorStatusCode status : values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + value);
    }
}