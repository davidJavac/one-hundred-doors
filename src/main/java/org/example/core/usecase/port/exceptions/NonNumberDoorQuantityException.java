package org.example.core.usecase.port.exceptions;

public class NonNumberDoorQuantityException extends RuntimeException {

    public NonNumberDoorQuantityException() {
        super("quantity field must be a number");
    }
}
