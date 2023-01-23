package org.example.core.usecase.port;

import org.example.core.usecase.port.dto.InputDto;

@FunctionalInterface
public interface DoorsExecutor {

    void execute(InputDto inputDto);
}
