package usecase;

import org.example.core.entity.Door;
import org.example.core.usecase.DoorVisitor;
import org.example.core.usecase.DoorVisitorExecutor;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.InputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DoorVisitorExecutorTest {

    private DoorVisitor doorVisitor;
    private Reporter reporter;
    private DoorsExecutor doorsExecutor;

    @BeforeEach
    public void setUp() {
        this.doorVisitor = mock(DoorVisitor.class);
        when(doorVisitor.execute(any())).thenReturn(buildArrayOfDoors());
        this.reporter = mock(Reporter.class);
        this.doorsExecutor = new DoorVisitorExecutor(doorVisitor, reporter);
    }

    @Test
    public void test_when_execute_then_should_invoke_door_visitor() {
        doorsExecutor.execute(buildInputDto());

        verify(doorVisitor, times(1)).execute(any());
    }

    @Test
    public void test_when_execute_then_should_invoke_result_reporter() {
        doorsExecutor.execute(buildInputDto());

        verify(reporter, times(1)).result(any());
    }

    private InputDto buildInputDto() {
        return new InputDto(100);
    }

    private Door [] buildArrayOfDoors() {
        IntStream oneHundredDoorsNumbers = IntStream.range(1, 101);
        return oneHundredDoorsNumbers.mapToObj(n -> Door.createWithNumber(n)).toArray(Door[]::new);
    }
}
