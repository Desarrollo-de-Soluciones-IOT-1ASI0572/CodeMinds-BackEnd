package com.codeminds.edugo.assignments.interfaces.rest.resources;

public record CreateWristbandResource(
        String rfidCode,
        String wristbandStatus,
        Long studentId
) {}
