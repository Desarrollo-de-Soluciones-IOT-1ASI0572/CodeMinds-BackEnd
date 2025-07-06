package com.codeminds.edugo.assignment.domain.services.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.assignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.assignment.domain.models.entities.SensorScan;

import java.util.Optional;

public interface SensorScanCommandService {
    Optional<SensorScan> handle(CreateSensorScanCommand command);
    void handle(DeleteSensorScanCommand command);
}
