package org.example.utils;

import org.example.core.entity.Door;
import org.example.core.usecase.port.dto.DoorDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DoorUtil {

    public static Door[] buildArrayOfDoors(Integer doorsQuantity) {
        return mapToStreamDoor(rangeDoorNumbers(doorsQuantity)).toArray(Door[]::new);
    }

    public static List<Door> buildListOfDoors(Integer doorsQuantity) {
        return mapToStreamDoor(rangeDoorNumbers(doorsQuantity)).toList();
    }

    public static DoorDto[] mapToArrayOfDoorDto(Door [] doors) {
        return Arrays.stream(doors).map(d -> new DoorDto(d)).toArray(DoorDto[]::new);
    }

    private static IntStream rangeDoorNumbers(Integer doorsQuantity) {
        return IntStream.range(1, doorsQuantity + 1);
    }

    private static Stream<Door> mapToStreamDoor(IntStream doorNumbers) {
        return doorNumbers.mapToObj(n -> Door.createWithNumber(n));
    }
}
