package org.example.config.spring.bean;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.config.AppModule;
import org.example.core.usecase.port.DoorsExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    private final Injector injector;

    public Beans() {
        injector = Guice.createInjector(new AppModule());
    }

    @Bean
    public DoorsExecutor doorsExecutorBean() {
        return injector.getInstance(DoorsExecutor.class);
    }
}
