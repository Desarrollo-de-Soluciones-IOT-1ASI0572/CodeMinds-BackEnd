package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.commands.CreateSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.commands.DeleteSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;

import java.util.Optional;

public interface SensorScanCommandService {
    Optional<SensorScan> handle(CreateSensorScanCommand command);
    void handle(DeleteSensorScanCommand command);
}
