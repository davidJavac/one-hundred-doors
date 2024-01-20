package org.example.adapter.presenter.rest;

import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.OutputDto;

import java.util.Optional;

public class RestReporter implements Reporter {

    @Override
    public Optional result(OutputDto outputDto) {
        return Optional.of(outputDto);
    }
}
