package com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan;

import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;

public record CreateSensorScanResource(
        ScanType scanType,
        Long wristbandId,
        Long tripId
) {}
