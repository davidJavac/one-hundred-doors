package org.example.core.usecase;

import org.example.core.entity.Door;

import java.util.List;

public class DoorVisitor {
    private Integer interval;

    public Door[] execute(List<Door> doors) {
        this.interval = 1;
        while(interval <= doors.size()) {
            visitAllDoorsByInterval(doors);
            incrementIntervalBy(1);
        }
        return doors.toArray(Door[]::new);
    }

    private void visitAllDoorsByInterval(List<Door> doors) {
        for (int i = interval - 1; i < doors.size(); i += interval) {
            visit(doors.get(i));
        }
    }

    private void incrementIntervalBy(Integer number) {
        interval += number;
    }

    private void visit(Door door) {
        door.toggle();
    }
}
