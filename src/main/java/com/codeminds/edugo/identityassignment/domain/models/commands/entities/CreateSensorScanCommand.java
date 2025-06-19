package com.codeminds.edugo.identityassignment.domain.models.commands.entities;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;

import java.time.LocalDateTime;

public record CreateSensorScanCommand (
        ScanType scanType,
        LocalDateTime scanTime,
        Wristband wristband
){}
