package com.sparta.msa_exam.order.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("errorMessage : " + ex.getMessage());
    }
}
