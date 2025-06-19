package com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.sensorscan;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan.CreateSensorScanResource;
import java.time.LocalDateTime;

public class CreateSensorScanCommandFromResourceAssembler {
    public static CreateSensorScanCommand toCommandFromResource(CreateSensorScanResource resource) {
        return new CreateSensorScanCommand(
                resource.scanType(),
                LocalDateTime.now(),
                resource.wristband().getId()
        );
    }
}
