package org.example.config;

import com.google.inject.AbstractModule;
import org.example.adapter.presenter.ConsoleReporter;
import org.example.core.usecase.DoorVisitorExecutor;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DoorsExecutor.class).to(DoorVisitorExecutor.class);
        bind(Reporter.class).to(ConsoleReporter.class);
    }
}

