package org.example.core.usecase.port.dto;

import org.example.core.entity.Door;

public class DoorDto {

    private Integer numberOfDoor;
    private String status;

    public DoorDto() {}

    public DoorDto(Door door) {
        this.numberOfDoor = door.number();
        this.status = door.state();
    }

    public Integer getNumberOfDoor() {
        return this.numberOfDoor;
    }

    public String getStatus() {
        return this.status;
    }
}
