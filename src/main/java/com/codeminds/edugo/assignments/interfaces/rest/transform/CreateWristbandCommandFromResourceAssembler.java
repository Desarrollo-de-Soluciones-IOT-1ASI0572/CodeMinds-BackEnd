package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.commands.CreateWristbandCommand;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateWristbandResource;

public class CreateWristbandCommandFromResourceAssembler {
    public static CreateWristbandCommand toCommandFromResource(CreateWristbandResource resource) {
        return new CreateWristbandCommand(
                resource.rfidCode(),
                resource.wristbandStatus(),
                resource.studentId()
        );
    }
}

