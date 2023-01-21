package entity;

import static enums.DoorStatus.OPEN;
import static enums.DoorStatus.CLOSED;

public class Door {

    private String state;

    public Door(String state) {
        this.state = state;
    }

    private void open() {
        this.state = OPEN.name();
    }

    private void close() {
        this.state = CLOSED.name();
    }

    public String state() {
        return this.state;
    }

    public void toggle() {
        if (OPEN.name().equalsIgnoreCase(this.state)) {
            close();
        }
        else {
            open();
        }
    }
}
