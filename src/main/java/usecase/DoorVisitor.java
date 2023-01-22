package usecase;

import entity.Door;

import java.util.List;

public class DoorVisitor {

    private List<Door> doors;

    public DoorVisitor(List<Door> doors) {
        this.doors = doors;
    }

    public Door[] execute() {
        for (Door door : doors) {
            visit(door);
        }

        return doors.toArray(Door[]::new);
    }

    private void visit(Door door) {
        door.toggle();
    }
}
