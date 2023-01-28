package org.example.core.usecase.port.exceptions;

public class NonPositiveDoorQuantityException extends RuntimeException {

    public NonPositiveDoorQuantityException() {
        super("quantity field must be higher than zero");
    }
}
