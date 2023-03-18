package org.example.core.usecase;

import com.google.inject.Inject;
import org.example.core.entity.Door;
import org.example.core.usecase.port.DoorsExecutor;
import org.example.core.usecase.port.Reporter;
import org.example.core.usecase.port.dto.InputDto;
import org.example.core.usecase.port.dto.OutputDto;

import java.util.Optional;

import static org.example.utils.DoorUtil.mapToArrayOfDoorDto;
import static org.example.utils.DoorUtil.buildListOfDoors;

public class DoorVisitorExecutor implements DoorsExecutor {

    private DoorVisitor doorVisitor;
    private Reporter reporter;

    public DoorVisitorExecutor () {}

    @Inject
    public DoorVisitorExecutor(DoorVisitor doorVisitor, Reporter reporter) {
        this.doorVisitor = doorVisitor;
        this.reporter = reporter;
    }

    @Override
    public Optional execute(InputDto inputDto) {
        Door [] visitedDoors = doorVisitor.execute(buildListOfDoors(inputDto.getDoorQuantity()));
        return reporter.result(new OutputDto(mapToArrayOfDoorDto(visitedDoors)));
    }
}
