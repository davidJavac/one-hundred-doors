package org.example.core.usecase.port.exceptions;

public class NullDoorQuantityException extends RuntimeException {

    public NullDoorQuantityException() {
        super("quantity field must not be null");
    }
}
