package com.codeminds.edugo.assignment.interfaces.rest.transform.entities.wristband;

import com.codeminds.edugo.assignment.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband.CreateWristbandResource;

public class CreateWristbandCommandFromResourceAssembler {
    public static CreateWristbandCommand toCommandFromResource(CreateWristbandResource resource) {
        return new CreateWristbandCommand(
                resource.rfidCode(),
                resource.wristbandStatus(),
                resource.studentId()
        );
    }
}

