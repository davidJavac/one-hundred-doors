package org.example.core.usecase.port.dto;

public class OutputDto {

    private DoorDto doors [];

    public OutputDto() {}

    public OutputDto(DoorDto doors []) {
        this.doors = doors;
    }

    public DoorDto [] getDoors() {
        return this.doors;
    }
}
