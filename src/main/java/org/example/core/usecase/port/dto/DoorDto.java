package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer numberOrDoor;
    private String state;

    public DoorDto(Door door) {
        this.numberOrDoor = door.number();
        this.state = door.state();
    }

    public Integer getNumberOrDoor() {
        return this.numberOrDoor;
    }

    public String getState() {
        return this.state;
    }
}
