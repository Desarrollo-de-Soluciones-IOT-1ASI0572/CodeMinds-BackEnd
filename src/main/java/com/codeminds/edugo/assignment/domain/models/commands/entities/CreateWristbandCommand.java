package com.codeminds.edugo.assignment.domain.models.commands.entities;

public record CreateWristbandCommand(
        String rfidCode,
        String wristbandStatus,
        Long studentId
) {}

