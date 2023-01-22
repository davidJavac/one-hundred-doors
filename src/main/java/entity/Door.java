package entity;

import static enums.DoorStatus.OPEN;
import static enums.DoorStatus.CLOSED;

public class Door {

    private String state;
    private Integer number;
    private Boolean wasToggled;

    public Door(Integer number, String state) {
        this.number = number;
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

    public Integer number() {
        return this.number;
    }

    public void toggle() {
        if (OPEN.name().equalsIgnoreCase(this.state)) {
            close();
        }
        else {
            open();
        }
        this.wasToggled = Boolean.TRUE;
    }

    public Boolean wasToggled() {
        return this.wasToggled;
    }
}
