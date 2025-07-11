package com.codeminds.edugo.assignments.domain.models.commands;

import com.codeminds.edugo.assignments.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record CreateSensorScanCommand(
        ScanType scanType,
        LocalDateTime scanTime,
        Long wristbandId
) {}

