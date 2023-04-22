package usecase;

import org.example.core.entity.Door;
import org.example.core.usecase.DoorVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.core.enums.DoorStatus.CLOSED;
import static org.example.core.enums.DoorStatus.OPEN;
import static org.example.utils.DoorUtil.buildListOfDoors;

public class DoorVisitorTest {
    private DoorVisitor doorVisitor;

    @BeforeEach
    public void setUp() {
        this.doorVisitor = new DoorVisitor();
    }

    @Test
    public void test_when_execute_then_doors_should_be_toggled() {
        Door[] result = doorVisitor.execute(buildListOfDoors(100));

        Assertions.assertTrue(wereAllDoorsToggled(result));
    }

    @Test
    public void test_when_execute_then_should_return_an_array_of_doors() {
        Door[] result = doorVisitor.execute(buildListOfDoors(100));

        Assertions.assertTrue(result.length > 0);
    }

    @Test
    public void test_when_execute_then_interval_of_visit_should_increment_by_one() {
        String expectedOpenState = OPEN.name();
        String expectedClosedState = CLOSED.name();

        Door[] result = doorVisitor.execute(buildListOfDoors(100));

        Assertions.assertEquals(expectedOpenState, result[0].state());
        Assertions.assertEquals(expectedClosedState, result[1].state());
        Assertions.assertEquals(expectedClosedState, result[2].state());
        Assertions.assertEquals(expectedOpenState, result[3].state());
        Assertions.assertEquals(expectedOpenState, result[99].state());
    }

    private Boolean wereAllDoorsToggled(Door [] doors) {
        return Arrays.stream(doors).allMatch(door -> door.wasToggled());
    }
}
