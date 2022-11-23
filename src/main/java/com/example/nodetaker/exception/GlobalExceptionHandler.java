package com.example.nodetaker.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleGenericException(GenericException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        errors.put("status", exception.getHttpStatus());
        errors.put("timestamp", exception.getDate());
        return ResponseEntity.status(exception.getHttpStatus()).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        GenericException ex = GenericException.builder()
                .date(new Date())
                .message(exception.getMessage())
                .httpStatus(HttpStatus.EXPECTATION_FAILED)
                .build();
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        errors.put("status", ex.getHttpStatus());
        errors.put("timestamp", ex.getDate()) ;
        return ResponseEntity.status(ex.getHttpStatus()).body(errors);

    }
}
