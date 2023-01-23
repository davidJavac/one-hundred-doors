package org.example.core.usecase.port;

import org.example.core.entity.Door;

import java.util.List;

@FunctionalInterface
public interface DoorsExecutor {

    void execute(List<Door> doors);
}
