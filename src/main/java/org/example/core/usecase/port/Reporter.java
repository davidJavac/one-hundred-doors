package org.example.core.usecase.port;

import org.example.core.usecase.port.dto.OutputDto;

@FunctionalInterface
public interface Reporter {

    void result(OutputDto outputDto);
}
