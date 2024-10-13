package com.mgmetehan.userjwtsecurityexample2.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GeneralException extends RuntimeException {
    private final String message;
    private final String code;
    private final Boolean repeatable;
    private final HttpStatus httpStatus;

    public GeneralException(ErrorStatusCode err, String... params) {
        super();
        this.message = String.format(err.getDescription(), params);
        this.code = String.valueOf(err.getValue());
        this.repeatable = err.isRepeatable();
        this.httpStatus = err.getHttpStatus();
    }
}
