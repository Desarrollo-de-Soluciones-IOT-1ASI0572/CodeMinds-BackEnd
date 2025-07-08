package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.UpdateTripStatusCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.UpdateTripStatusResource;

public class UpdateTripStatusCommandFromResourceAssembler {

    public static UpdateTripStatusCommand toCommandFromResource(Long tripId, UpdateTripStatusResource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("UpdateTripStatusResource cannot be null");
        }
        return new UpdateTripStatusCommand(tripId, resource.status());
    }
}
