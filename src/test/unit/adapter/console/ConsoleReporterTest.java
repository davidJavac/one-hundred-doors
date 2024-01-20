package adapter.console;

import org.example.adapter.presenter.console.ConsoleReporter;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.OutputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.example.core.enums.DoorStatus.CLOSED;
import static org.example.utils.DoorUtil.buildArrayOfDoors;
import static org.example.utils.DoorUtil.mapToArrayOfDoorDto;

public class ConsoleReporterTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void test_when_execute_console_reporter_then_should_print_headers() {
        String expectedNumberHeader = "door_number";
        String expectedStateHeader = "state";

        Reporter reporter = new ConsoleReporter();
        reporter.result(new OutputDto(mapToArrayOfDoorDto(buildArrayOfDoors(100))));

        Assertions.assertEquals(expectedNumberHeader, valueByCell(0, 0));
        Assertions.assertEquals(expectedStateHeader, valueByCell(0, 1));
    }

    @Test
    public void test_when_execute_console_reporter_then_should_print_number_of_the_door_and_its_state() {
        String expectedNumber = "1";
        String expectedState = CLOSED.name();

        Reporter reporter = new ConsoleReporter();
        reporter.result(new OutputDto(mapToArrayOfDoorDto(buildArrayOfDoors(1))));

        Assertions.assertEquals(expectedNumber, valueByCell(1, 0));
        Assertions.assertEquals(expectedState, valueByCell(1, 1));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(System.out);
    }

    private String valueByCell(Integer row, Integer column) {
        return outputStream.toString().split("\\r?\\n")[row].split(" ")[column];
    }
}
