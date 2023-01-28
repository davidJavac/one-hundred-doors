package org.example.core.usecase.port.exceptions;

public class ExceededLimitDoorQuantityException extends RuntimeException {

    public ExceededLimitDoorQuantityException() {
        super("quantity field must be less or equal to 1000");
    }
}
