package com.codeminds.edugo.assignments.interfaces.rest.resources;

import com.codeminds.edugo.assignments.domain.models.valueobjects.ScanType;

public record CreateSensorScanResource(
        ScanType scanType,
        Long wristbandId,
        Long tripId
) {}
