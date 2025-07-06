package com.codeminds.edugo.assignment.interfaces.rest.resources.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record SensorScanResource(
        Long id,
        LocalDateTime scanTime,
        ScanType scanType,
        Long wristbandId
) {}

