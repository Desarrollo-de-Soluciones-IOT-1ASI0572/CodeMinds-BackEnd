package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.EndRouteCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.EndRouteResource;

import java.time.LocalDateTime;

public class EndRouteCommandFromResourceAssembler {
    public static EndRouteCommand toCommandFromResource(EndRouteResource resource) {
        return new EndRouteCommand(
                resource.tripId(),
                LocalDateTime.now()
        );
    }
}
