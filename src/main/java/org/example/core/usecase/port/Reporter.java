package org.example.core.usecase.port;

import org.example.core.entity.Door;

@FunctionalInterface
public interface Reporter {

    void result(Door[] doors);
}
