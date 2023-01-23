package org.example.core.usecase.port.dto;

public class DoorDto {

    private Integer number;
    private String state;

    public DoorDto(Integer number, String state) {
        this.number = number;
        this.state = state;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getState() {
        return this.state;
    }
}
