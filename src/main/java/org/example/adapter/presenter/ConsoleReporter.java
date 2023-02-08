package org.example.adapter.presenter;

import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.DoorDto;
import org.example.core.usecase.port.dto.OutputDto;

public class ConsoleReporter implements Reporter {

    @Override
    public void result(OutputDto outputDto) {
        printHeaders();
        printContentOf(outputDto);
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
