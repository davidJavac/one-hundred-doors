package org.example.adapter.presenter;

import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.OutputDto;

public class ConsoleReporter implements Reporter {
    @Override
    public void result(OutputDto outputDto) {
        System.out.println("door_number state");
    }
}
