package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.StartRouteResource;

public class StartRouteCommandFromResourceAssembler {
    public static StartRouteCommand toCommandFromResource(StartRouteResource resource) {
        return new StartRouteCommand(
                resource.tripId()
        );
    }
}
