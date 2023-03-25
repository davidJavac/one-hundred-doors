package org.example.adapter.error.rest;

import org.example.core.usecase.port.exceptions.NonNumberDoorQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NonNumberDoorQuantityException.class)
    public ResponseEntity<ErrorResponse> handleNonNumberDoorQuantityException(NonNumberDoorQuantityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}