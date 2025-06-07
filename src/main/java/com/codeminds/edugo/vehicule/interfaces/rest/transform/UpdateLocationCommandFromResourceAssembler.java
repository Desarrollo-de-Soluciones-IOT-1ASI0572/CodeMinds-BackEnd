package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.UpdateLocationCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.UpdateLocationResource;

public class UpdateLocationCommandFromResourceAssembler {
    public static UpdateLocationCommand toCommandFromResource(UpdateLocationResource resource) {
        return new UpdateLocationCommand(
                resource.vehicleId(),
                resource.latitude(),
                resource.longitude(),
                resource.speed()
        );
    }
}
