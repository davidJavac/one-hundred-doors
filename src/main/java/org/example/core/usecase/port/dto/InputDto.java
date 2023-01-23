package org.example.core.usecase.port.dto;

public class InputDto {

    private Integer doorQuantity;

    public InputDto(Integer doorQuantity) {
        this.doorQuantity = doorQuantity;
    }

    public Integer getDoorQuantity() {
        return this.doorQuantity;
    }
}
