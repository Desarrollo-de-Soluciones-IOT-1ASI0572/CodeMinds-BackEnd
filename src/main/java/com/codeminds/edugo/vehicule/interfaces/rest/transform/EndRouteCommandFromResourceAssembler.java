package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.EndRouteCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.EndRouteResource;

import java.time.LocalDateTime;

public class EndRouteCommandFromResourceAssembler {
    public static EndRouteCommand toCommandFromResource(EndRouteResource resource) {
        return new EndRouteCommand(
                resource.tripId(),
                LocalDateTime.now() // tiempo actual como hora de finalización
        );
    }
}
