package adapter;

import org.example.adapter.presenter.ConsoleReporter;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.OutputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        String expectedHeaders = "door_number state";

        Reporter reporter = new ConsoleReporter();
        reporter.result(new OutputDto(mapToArrayOfDoorDto(buildArrayOfDoors(100))));

        Assertions.assertEquals(expectedHeaders, outputStream.toString().trim());
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(System.out);
    }
}
