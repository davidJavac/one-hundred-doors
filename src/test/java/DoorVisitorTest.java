import entity.Door;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.DoorVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static enums.DoorStatus.CLOSED;
import static enums.DoorStatus.OPEN;

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

        Assertions.assertTrue(result.length > 0);
    }

    @Test
    public void test_when_execute_then_interval_of_visit_should_increment_by_one() {
        DoorVisitor doorVisitor = new DoorVisitor(buildListOfDoors());

        String expectedOpenState = OPEN.name();
        String expectedClosedState = CLOSED.name();

        Door[] result = doorVisitor.execute();

        Assertions.assertEquals(expectedOpenState, result[0].state());
        Assertions.assertEquals(expectedClosedState, result[1].state());
        Assertions.assertEquals(expectedClosedState, result[2].state());
        Assertions.assertEquals(expectedOpenState, result[3].state());
        Assertions.assertEquals(expectedOpenState, result[99].state());
    }

    private List<Door> buildListOfDoors() {
        IntStream oneHundredDoorsNumbers = IntStream.range(1, 101);
        return oneHundredDoorsNumbers.mapToObj(n -> new Door(n, CLOSED.name())).toList();
    }

    private Boolean wereAllDoorsToggled(Door [] doors) {
        return Arrays.stream(doors).allMatch(door -> door.wasToggled());
    }
}
