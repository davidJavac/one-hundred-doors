package usecase;

import org.example.core.usecase.DoorVisitor;
import org.example.core.usecase.DoorVisitorExecutor;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.InputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.example.utils.DoorUtil.buildArrayOfDoors;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DoorVisitorExecutorTest {

    private DoorVisitor doorVisitor;
    private Reporter reporter;
    private DoorsExecutor doorsExecutor;

    @BeforeEach
    public void setUp() {
        this.doorVisitor = mock(DoorVisitor.class);
        when(doorVisitor.execute(any())).thenReturn(buildArrayOfDoors(100));
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
        when(reporter.result(any())).thenReturn(Optional.empty());
        
        verify(reporter, times(1)).result(any());
    }

    private InputDto buildInputDto() {
        return new InputDto("100");
    }

}
