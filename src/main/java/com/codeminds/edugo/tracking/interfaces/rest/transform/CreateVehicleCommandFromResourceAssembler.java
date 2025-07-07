package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.CreateVehicleCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(resource.driverId(), resource.capacity());
    }
}