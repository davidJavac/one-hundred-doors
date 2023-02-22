package org.example.core.usecase.port.dto;

import org.example.core.usecase.port.exceptions.ExceededLimitDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonNumberDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonPositiveDoorQuantityException;
import org.example.core.usecase.port.exceptions.NullDoorQuantityException;

import java.util.Objects;

public class InputDto {

    private String inputDoorQuantity;
    private Integer doorQuantity;
    private Integer FLOOR_LIMIT = 0;
    private Integer CEIL_LIMIT = 1000;

    public InputDto(String inputDoorQuantity) {
        this.inputDoorQuantity = inputDoorQuantity;
        validate();
    }

    public Integer getDoorQuantity() {
        return this.doorQuantity;
    }

    private void validate() {
        if (Objects.isNull(inputDoorQuantity) || inputDoorQuantity.isBlank()) {
            throw new NullDoorQuantityException();
        }

        validateNumber();

        if (doorQuantity.compareTo(FLOOR_LIMIT) <= 0) {
            throw new NonPositiveDoorQuantityException();
        }

        if (doorQuantity.compareTo(CEIL_LIMIT) > 0) {
            throw new ExceededLimitDoorQuantityException();
        }
    }

    private void validateNumber() {
        try {
            Integer.parseInt(inputDoorQuantity);
            this.doorQuantity = Integer.valueOf(inputDoorQuantity);
        } catch (NumberFormatException e) {
            throw new NonNumberDoorQuantityException();
        }
    }
}
