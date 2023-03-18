package adapter;

import org.example.adapter.controller.DoorConsoleController;
import org.example.core.usecase.port.DoorsExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DoorConsoleControllerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayInputStream input = new ByteArrayInputStream(Integer.valueOf(100).toString().getBytes());
    private DoorsExecutor doorsExecutor;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        System.setIn(input);
        this.doorsExecutor = mock(DoorsExecutor.class);
    }

    @Test
    public void test_when_execute_then_should_ask_for_amount_of_doors() {
        DoorConsoleController doorConsoleController = new DoorConsoleController(doorsExecutor);
        String expectedPromptMsg = "Enter the amount of doors:";

        doorConsoleController.execute();

        Assertions.assertEquals(expectedPromptMsg, outputStream.toString().trim());
    }

    @Test
    public void test_when_execute_then_should_invoke_doors_executor() {
        DoorConsoleController doorConsoleController = new DoorConsoleController(doorsExecutor);
        when(doorsExecutor.execute(any())).thenReturn(Optional.empty());

        doorConsoleController.execute();

        verify(doorsExecutor, times(1)).execute(any());
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(System.out);
        System.setIn(System.in);
    }
}
