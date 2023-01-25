package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer number;
    private String state;

    public DoorDto(Door door) {
        this.number = door.number();
        this.state = door.state();
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getState() {
        return this.state;
    }
}
