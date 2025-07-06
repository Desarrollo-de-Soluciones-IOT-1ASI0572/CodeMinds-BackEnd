package com.codeminds.edugo.assignment.interfaces.rest.transform.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.sensorscan.CreateSensorScanResource;
import java.time.LocalDateTime;

public class CreateSensorScanCommandFromResourceAssembler {
    public static CreateSensorScanCommand toCommandFromResource(CreateSensorScanResource resource) {
        return new CreateSensorScanCommand(
                resource.scanType(),
                LocalDateTime.now(),
                resource.wristbandId(),
                resource.tripId()
        );
    }
}

