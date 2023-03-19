package org.example;

import com.google.inject.Guice;
import org.example.adapter.controller.console.DoorConsoleController;
import org.example.config.AppModule;
import org.example.core.usecase.port.DoorsExecutor;

public class Main {

    public static void main(String[] args) {
        DoorsExecutor doorsExecutor = Guice.createInjector(new AppModule()).getInstance(DoorsExecutor.class);
        DoorConsoleController doorConsoleController = new DoorConsoleController(doorsExecutor);
        doorConsoleController.execute();
    }
}