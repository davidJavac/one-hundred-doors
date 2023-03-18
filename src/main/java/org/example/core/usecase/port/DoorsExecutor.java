package org.example.core.usecase.port;

import org.example.core.usecase.port.dto.InputDto;

import java.util.Optional;

@FunctionalInterface
public interface DoorsExecutor {

    Optional execute(InputDto inputDto);
}
