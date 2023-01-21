import entity.Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static enums.DoorStatus.CLOSED;
import static enums.DoorStatus.OPEN;

public class DoorTest {

    @Test
    public void test_when_a_door_is_toggled_then_should_inverse_its_state() {
        String openState = OPEN.name();
        String closedState = CLOSED.name();
        Door door = new Door(closedState);

        door.toggle();
        Assertions.assertEquals(openState, door.state());
        door.toggle();
        Assertions.assertEquals(closedState, door.state());
    }

}
