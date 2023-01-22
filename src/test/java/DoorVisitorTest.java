import entity.Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.DoorVisitor;

import java.util.Arrays;

import static enums.DoorStatus.CLOSED;
import static enums.DoorStatus.OPEN;

public class DoorVisitorTest {

    @Test
    public void test_when_visiting_a_list_of_doors_then_should_be_toggled() {
        Door firstDoor = new Door(CLOSED.name());
        Door secondDoor = new Door(CLOSED.name());
        DoorVisitor doorVisitor = new DoorVisitor(Arrays.asList(firstDoor, secondDoor));
        String openState = OPEN.name();

        doorVisitor.visit(firstDoor);
        doorVisitor.visit(secondDoor);

        Assertions.assertEquals(openState, firstDoor.state());
        Assertions.assertEquals(openState, secondDoor.state());
    }
}
