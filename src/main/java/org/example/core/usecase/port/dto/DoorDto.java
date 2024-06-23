package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer number;
    private String doorState;

    public DoorDto() {}

    public DoorDto(Door door) {
        this.number = door.number();
        this.doorState = door.state();
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getDoorState() {
        return this.doorState;
    }
}
