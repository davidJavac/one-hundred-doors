package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer number;
    private String status;

    public DoorDto() {}

    public DoorDto(Door door) {
        this.number = door.number();
        this.status = door.state();
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getStatus() {
        return this.status;
    }
}
