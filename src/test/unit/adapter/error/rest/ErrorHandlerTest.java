package adapter.error.rest;

import org.example.adapter.error.rest.ErrorHandler;
import org.example.core.usecase.port.exceptions.ExceededLimitDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonNumberDoorQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorHandlerTest {

    private ErrorHandler errorHandler;

    @BeforeEach
    public void setUp () {
        this.errorHandler = new ErrorHandler();
    }

    @Test
    public void test_when_NonNumberDoorQuantityException_then_it_should_intercept_error () {
        ResponseEntity responseEntity = errorHandler.handleNonNumberDoorQuantityException(new NonNumberDoorQuantityException());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void test_when_ExceededLimitDoorQuantityException_then_it_should_intercept_error () {
        ResponseEntity responseEntity = errorHandler.handleExceededLimitDoorQuantityException(new ExceededLimitDoorQuantityException());

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
    }
}
