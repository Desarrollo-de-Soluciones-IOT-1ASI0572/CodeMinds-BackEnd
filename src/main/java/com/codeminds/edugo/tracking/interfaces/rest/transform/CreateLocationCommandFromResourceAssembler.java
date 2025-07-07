package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.CreateLocationCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.CreateLocationResource;

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
