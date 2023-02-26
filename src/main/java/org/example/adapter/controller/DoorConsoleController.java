package org.example.adapter.controller;

import com.google.inject.Inject;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.dto.InputDto;

import java.util.Scanner;

public class DoorConsoleController {

    private DoorsExecutor doorsExecutor;

    @Inject
    public DoorConsoleController (DoorsExecutor doorsExecutor) {
        this.doorsExecutor = doorsExecutor;
    }

    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the amount of doors:");

        String doorsAmount = scan.nextLine();
        scan.close();

        doorsExecutor.execute(new InputDto(doorsAmount));
    }
}
