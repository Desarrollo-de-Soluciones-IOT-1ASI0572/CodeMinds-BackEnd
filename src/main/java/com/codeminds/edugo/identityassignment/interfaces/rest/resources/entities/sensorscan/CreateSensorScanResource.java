package com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record CreateSensorScanResource(
        ScanType scanType,
        Long wristbandId
) {}
