package com.example.backend.exception;

import com.example.backend.exception.exceptions.EntityAlreadyExistsException;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorDto handleInternalServerError(Exception ex) {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException(Exception ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = ex.getMessage();

        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> handleEntityAlreadyExistsException(Exception ex) {
        HttpStatus status = HttpStatus.OK;
        String message = ex.getMessage();

        return new ResponseEntity<>(message, status);
    }

}