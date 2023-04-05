package org.example.adapter.error.rest;

import org.example.core.usecase.port.exceptions.ExceededLimitDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonNumberDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonPositiveDoorQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NonNumberDoorQuantityException.class)
    public ResponseEntity<ErrorResponse> handleNonNumberDoorQuantityException(NonNumberDoorQuantityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceededLimitDoorQuantityException.class)
    public ResponseEntity<ErrorResponse> handleExceededLimitDoorQuantityException(ExceededLimitDoorQuantityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonPositiveDoorQuantityException.class)
    public ResponseEntity<ErrorResponse> handleNonPositiveDoorQuantityException(NonPositiveDoorQuantityException ex) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
