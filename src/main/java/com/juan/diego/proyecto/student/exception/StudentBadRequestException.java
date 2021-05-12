package com.juan.diego.proyecto.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentBadRequestException extends RuntimeException{
    public StudentBadRequestException(String message) {
        super(message);
    }
}
