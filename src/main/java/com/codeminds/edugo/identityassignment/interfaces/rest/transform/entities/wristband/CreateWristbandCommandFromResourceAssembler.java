package com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.wristband;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.wristband.CreateWristbandResource;

public class CreateWristbandCommandFromResourceAssembler {
    public static CreateWristbandCommand toCommandFromResource(CreateWristbandResource resource) {
        return new CreateWristbandCommand(
                resource.rfidCode(),
                resource.wristbandStatus(),
                resource.student().getId()
        );
    }
}
