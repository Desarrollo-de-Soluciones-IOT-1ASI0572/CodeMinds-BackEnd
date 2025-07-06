package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;

import java.util.Optional;

public interface SensorScanCommandService {
    Optional<SensorScan> handle(CreateSensorScanCommand command);
    void handle(DeleteSensorScanCommand command);
}
