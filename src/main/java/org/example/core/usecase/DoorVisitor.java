package org.example.core.usecase;

import org.example.core.entity.Door;

import java.util.List;

public class DoorVisitor {

    private List<Door> doors;
    private Integer interval;

    public DoorVisitor(List<Door> doors) {
        this.doors = doors;
        this.interval = 1;
    }

    public Door[] execute() {
        while(interval <= doors.size()) {
            visitAllDoorsByInterval();
            incrementIntervalBy(1);
        }
        return doors.toArray(Door[]::new);
    }

    private void visitAllDoorsByInterval() {
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
