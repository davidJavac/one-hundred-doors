package org.example.core.usecase.port;

import org.example.core.usecase.port.dto.OutputDto;

import java.util.Optional;

@FunctionalInterface
public interface Reporter {

    Optional result(OutputDto outputDto);
}
