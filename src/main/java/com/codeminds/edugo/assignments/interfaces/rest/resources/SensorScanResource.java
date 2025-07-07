package com.codeminds.edugo.assignments.interfaces.rest.resources;

import com.codeminds.edugo.assignments.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record SensorScanResource(
        Long id,
        LocalDateTime scanTime,
        ScanType scanType,
        Long wristbandId
) {}

