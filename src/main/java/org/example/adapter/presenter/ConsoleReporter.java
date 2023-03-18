package org.example.adapter.presenter;

import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.DoorDto;
import org.example.core.usecase.port.dto.OutputDto;

import java.util.Optional;

public class ConsoleReporter implements Reporter {

    @Override
    public Optional result(OutputDto outputDto) {
        printHeaders();
        printContentOf(outputDto);
        return Optional.empty();
    }

    private void printHeaders() {
        System.out.println("door_number state");
    }

    private void printContentOf(OutputDto outputDto) {
        for(DoorDto door : outputDto.getDoors()) {
            System.out.println(door.getNumber() + " " + door.getState());
        }
    }
}
