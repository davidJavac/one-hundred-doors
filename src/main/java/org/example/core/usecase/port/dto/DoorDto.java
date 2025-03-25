package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer number;
    private String doorStatus;

    public DoorDto() {}

    public DoorDto(Door door) {
        this.number = door.number();
        this.doorStatus = door.state();
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getDoorStatus() {
        return this.doorStatus;
    }
}
