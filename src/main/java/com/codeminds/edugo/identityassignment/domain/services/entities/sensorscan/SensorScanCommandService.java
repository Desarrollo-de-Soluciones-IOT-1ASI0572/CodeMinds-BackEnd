package com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;

import java.util.Optional;

public interface SensorScanCommandService {
    Optional<SensorScan> handle(CreateSensorScanCommand command);
    void handle(DeleteSensorScanCommand command);
}
