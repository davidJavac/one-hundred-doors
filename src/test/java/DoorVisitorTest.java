import entity.Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.DoorVisitor;

import java.util.Arrays;
import java.util.List;

import static enums.DoorStatus.CLOSED;

public class DoorVisitorTest {

    @Test
    public void test_when_execute_then_doors_should_be_toggled() {
        DoorVisitor doorVisitor = new DoorVisitor(buildListOfDoors());

        Door[] result = doorVisitor.execute();

        Assertions.assertTrue(wereAllDoorsToggled(result));
    }

    @Test
    public void test_when_execute_then_should_return_an_array_of_doors() {
        DoorVisitor doorVisitor = new DoorVisitor(buildListOfDoors());

        Door[] result = doorVisitor.execute();

        Assertions.assertEquals(3, result.length);
    }

    private List<Door> buildListOfDoors() {
        Door firstDoor = new Door(1, CLOSED.name());
        Door secondDoor = new Door(2, CLOSED.name());
        Door thirdDoor = new Door(3, CLOSED.name());
        return Arrays.asList(firstDoor, secondDoor, thirdDoor);
    }

    private Boolean wereAllDoorsToggled(Door [] doors) {
        return Arrays.stream(doors).allMatch(door -> door.wasToggled());
    }
}
