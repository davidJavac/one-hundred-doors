package org.example.utils;

import org.example.core.entity.Door;
import org.example.core.usecase.port.dto.DoorDto;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DoorUtil {

    private static Door[] buildArrayOfDoors(Integer limit) {
        IntStream oneHundredDoorsNumbers = IntStream.range(1, limit + 1);
        return oneHundredDoorsNumbers.mapToObj(n -> Door.createWithNumber(n)).toArray(Door[]::new);
    }

    private static DoorDto[] mapToDoorDto(Door [] doors) {
        return Arrays.stream(doors).map(d -> new DoorDto(d)).toArray(DoorDto[]::new);
    }
}
