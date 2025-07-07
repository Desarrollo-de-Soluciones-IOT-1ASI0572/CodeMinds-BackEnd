package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.commands.CreateSensorScanCommand;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateSensorScanResource;
import java.time.LocalDateTime;

public class CreateSensorScanCommandFromResourceAssembler {
    public static CreateSensorScanCommand toCommandFromResource(CreateSensorScanResource resource) {
        return new CreateSensorScanCommand(
                resource.scanType(),
                LocalDateTime.now(),
                resource.wristbandId()
        );
    }
}

