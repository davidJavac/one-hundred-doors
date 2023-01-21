import entity.Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.DoorVisitor;

import static enums.DoorStatus.CLOSED;
import static enums.DoorStatus.OPEN;

public class DoorVisitorTest {

    @Test
    public void test_when_visiting_a_door_then_should_be_toggled() {
        Door door = new Door(CLOSED.name());
        DoorVisitor doorVisitor = new DoorVisitor(door);
        String openState = OPEN.name();

        doorVisitor.visit();

        Assertions.assertEquals(openState, door.state());
    }
}
