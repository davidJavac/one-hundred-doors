package usecase;

import entity.Door;

public class DoorVisitor {

    private Door door;

    public DoorVisitor(Door door) {
        this.door = door;
    }

    public void visit() {
        this.door.toggle();
    }
}
