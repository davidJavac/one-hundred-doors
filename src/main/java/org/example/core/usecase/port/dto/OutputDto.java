package org.example.core.usecase.port.dto;

import java.util.List;

public class OutputDto {

    private List<DoorDto> doors;

    public OutputDto(List<DoorDto> doors) {
        this.doors = doors;
    }

    public List<DoorDto> getDoors() {
        return this.doors;
    }
}
