import org.example.core.entity.Door;
import org.example.core.usecase.DoorVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.example.core.enums.DoorStatus.CLOSED;
import static org.example.core.enums.DoorStatus.OPEN;

public class DoorVisitorTest {
    private DoorVisitor doorVisitor;

    @BeforeEach
    public void setUp() {
        this.doorVisitor = new DoorVisitor();
    }

    @Test
    public void test_when_execute_then_doors_should_be_toggled() {
        Door[] result = doorVisitor.execute(buildListOfDoors());

        Assertions.assertTrue(wereAllDoorsToggled(result));
    }

    @Test
    public void test_when_execute_then_should_return_an_array_of_doors() {
        Door[] result = doorVisitor.execute(buildListOfDoors());

        Assertions.assertTrue(result.length > 0);
    }

    @Test
    public void test_when_execute_then_interval_of_visit_should_increment_by_one() {
        String expectedOpenState = OPEN.name();
        String expectedClosedState = CLOSED.name();

        Door[] result = doorVisitor.execute(buildListOfDoors());

        Assertions.assertEquals(expectedOpenState, result[0].state());
        Assertions.assertEquals(expectedClosedState, result[1].state());
        Assertions.assertEquals(expectedClosedState, result[2].state());
        Assertions.assertEquals(expectedOpenState, result[3].state());
        Assertions.assertEquals(expectedOpenState, result[99].state());
    }

    private List<Door> buildListOfDoors() {
        IntStream oneHundredDoorsNumbers = IntStream.range(1, 101);
        return oneHundredDoorsNumbers.mapToObj(n -> Door.createDoor(n)).toList();
    }

    private Boolean wereAllDoorsToggled(Door [] doors) {
        return Arrays.stream(doors).allMatch(door -> door.wasToggled());
    }
}
