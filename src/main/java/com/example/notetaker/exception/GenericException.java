package com.example.notetaker.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;
    private Date date;

}
