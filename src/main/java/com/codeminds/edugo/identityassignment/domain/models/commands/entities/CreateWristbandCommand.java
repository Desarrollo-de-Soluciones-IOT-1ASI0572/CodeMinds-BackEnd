package com.codeminds.edugo.identityassignment.domain.models.commands.entities;

import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;

public record CreateWristbandCommand(
    String rfidCode,
    WristbandStatus wristbandStatus,
    Long studentId
) {}
