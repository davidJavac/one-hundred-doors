package usecase.port.dto;

import org.example.core.usecase.port.dto.InputDto;
import org.example.core.usecase.port.exceptions.ExceededLimitDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonPositiveDoorQuantityException;
import org.example.core.usecase.port.exceptions.NullDoorQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputDtoTest {

    @Test
    public void test_when_door_quantity_is_correctly_set_then_validation_should_run_ok() {
        InputDto inputDto = new InputDto(100);
        inputDto.validate();
    }

    @Test
    public void then_when_door_quantity_is_null_then_should_throw_an_exception() {
        InputDto inputDto = new InputDto(null);

        assertThrowOfTask(NullDoorQuantityException.class, () -> inputDto.validate(), "quantity field must not be null");
    }

    @Test
    public void then_when_door_quantity_is_less_than_0_then_should_throw_an_exception() {
        InputDto inputDto = new InputDto(-10);

        assertThrowOfTask(NonPositiveDoorQuantityException.class, () -> inputDto.validate(), "quantity field must be higher than zero");
    }

    @Test
    public void then_when_door_quantity_is_equal_to_0_then_should_throw_an_exception() {
        InputDto inputDto = new InputDto(0);

        assertThrowOfTask(NonPositiveDoorQuantityException.class, () -> inputDto.validate(), "quantity field must be higher than zero");
    }

    @Test
    public void then_when_door_quantity_is_higher_than_1000_then_should_throw_an_exception() {
        InputDto inputDto = new InputDto(1001);

        assertThrowOfTask(ExceededLimitDoorQuantityException.class, () -> inputDto.validate(), "quantity field must be less or equal to 1000");
    }

    private void assertThrowOfTask(Class exception, Runnable task, String expectedMsg) {
        Exception thrown = (Exception) Assertions.assertThrows(exception, () -> {
            task.run();
        });
        Assertions.assertEquals(expectedMsg, thrown.getMessage());
    }
}
