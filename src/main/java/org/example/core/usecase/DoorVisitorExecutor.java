package org.example.core.usecase;

import org.example.core.entity.Door;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.DoorDto;
import org.example.core.usecase.port.dto.InputDto;
import org.example.core.usecase.port.dto.OutputDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DoorVisitorExecutor implements DoorsExecutor {

    private DoorVisitor doorVisitor;
    private Reporter reporter;

    public DoorVisitorExecutor(DoorVisitor doorVisitor, Reporter reporter) {
        this.doorVisitor = doorVisitor;
        this.reporter = reporter;
    }

    @Override
    public void execute(InputDto inputDto) {
        Door [] visitedDoors = doorVisitor.execute(buildListOfInputDoors(inputDto.getDoorQuantity()));
        reporter.result(new OutputDto(mapToDoorDto(visitedDoors)));
    }

    private List<Door> buildListOfInputDoors(Integer doorsQuantity) {
        IntStream doorNumbers = IntStream.range(1, doorsQuantity + 1);
        return doorNumbers.mapToObj(n -> Door.createDoor(n)).toList();
    }

    private DoorDto [] mapToDoorDto(Door [] visitedDoors) {
        return Arrays.stream(visitedDoors).map(d -> new DoorDto(d)).toArray(DoorDto[]::new);
    }
}
