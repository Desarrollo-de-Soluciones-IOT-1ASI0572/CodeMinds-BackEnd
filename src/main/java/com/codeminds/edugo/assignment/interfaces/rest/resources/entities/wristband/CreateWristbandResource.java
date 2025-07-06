package com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband;

public record CreateWristbandResource(
        String rfidCode,
        String wristbandStatus,
        Long studentId
) {}
