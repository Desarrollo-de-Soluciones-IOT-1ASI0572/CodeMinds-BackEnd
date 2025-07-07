package com.codeminds.edugo.assignments.domain.models.commands;

public record CreateWristbandCommand(
        String rfidCode,
        String wristbandStatus,
        Long studentId
) {}

