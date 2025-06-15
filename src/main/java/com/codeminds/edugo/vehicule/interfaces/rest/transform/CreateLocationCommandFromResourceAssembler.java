package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.CreateLocationCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.CreateLocationResource;

public class CreateLocationCommandFromResourceAssembler {
    public static CreateLocationCommand toCommandFromResource(CreateLocationResource resource) {
        return new CreateLocationCommand(
                resource.vehicleId(),
                resource.tripId(),
                resource.latitude(),
                resource.longitude(),
                resource.speed(),
                resource.timestamp()
        );
    }
}
