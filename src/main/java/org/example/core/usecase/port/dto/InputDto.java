package org.example.core.usecase.port.dto;

import org.example.core.usecase.port.exceptions.ExceededLimitDoorQuantityException;
import org.example.core.usecase.port.exceptions.NonPositiveDoorQuantityException;
import org.example.core.usecase.port.exceptions.NullDoorQuantityException;

import java.util.Objects;

public class InputDto implements InputFieldsValidation {

    private Integer doorQuantity;
    private Integer FLOOR_LIMIT = 0;
    private Integer CEIL_LIMIT = 1000;

    public InputDto(Integer doorQuantity) {
        this.doorQuantity = doorQuantity;
    }

    public Integer getDoorQuantity() {
        return this.doorQuantity;
    }

    @Override
    public void validate() {
        if (Objects.isNull(doorQuantity)) {
            throw new NullDoorQuantityException();
        }

        if (doorQuantity.compareTo(FLOOR_LIMIT) <= 0) {
            throw new NonPositiveDoorQuantityException();
        }

        if (doorQuantity.compareTo(CEIL_LIMIT) > 0) {
            throw new ExceededLimitDoorQuantityException();
        }
    }
}
