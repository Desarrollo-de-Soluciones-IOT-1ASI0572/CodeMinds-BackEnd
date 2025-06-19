package com.codeminds.edugo.identityassignment.domain.models.commands.entities;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;

public record CreateWristbandCommand(
        String rfidCode,
        String wristbandStatus,
        Long studentId
) {}

