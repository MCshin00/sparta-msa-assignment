package com.sparta.msa_exam.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiException> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage());
        return new ResponseEntity<>(
                restApiException,
                HttpStatus.REQUEST_TIMEOUT
        );
    }
}
