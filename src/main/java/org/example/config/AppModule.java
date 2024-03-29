package org.example.config;

import com.google.inject.AbstractModule;
import org.example.adapter.presenter.rest.RestReporter;
import org.example.core.usecase.DoorVisitorExecutor;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Reporter.class).to(RestReporter.class);
        bind(DoorsExecutor.class).to(DoorVisitorExecutor.class);
    }
}

