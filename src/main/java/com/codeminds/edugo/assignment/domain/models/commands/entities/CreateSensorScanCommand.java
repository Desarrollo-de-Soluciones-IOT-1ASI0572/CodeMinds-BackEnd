package com.codeminds.edugo.assignment.domain.models.commands.entities;

import com.codeminds.edugo.assignment.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record CreateSensorScanCommand(
        ScanType scanType,
        LocalDateTime scanTime,
        Long wristbandId,
        Long tripId
) {}

