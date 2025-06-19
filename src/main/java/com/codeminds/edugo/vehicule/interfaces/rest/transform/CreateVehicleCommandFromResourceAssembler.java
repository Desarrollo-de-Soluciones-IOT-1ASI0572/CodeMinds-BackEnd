package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.CreateVehicleCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(resource.driverId(), resource.capacity());
    }
}