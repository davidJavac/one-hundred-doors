package usecase;

import entity.Door;

import java.util.List;

public class DoorVisitor {

    private List<Door> doors;

    public DoorVisitor(List<Door> doors) {
        this.doors = doors;
    }

    public void visit(Door door) {
        door.toggle();
    }
}
