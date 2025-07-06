package com.codeminds.edugo.assignment.interfaces.rest.resources.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.valueobjects.ScanType;

public record CreateSensorScanResource(
        ScanType scanType,
        Long wristbandId,
        Long tripId
) {}
