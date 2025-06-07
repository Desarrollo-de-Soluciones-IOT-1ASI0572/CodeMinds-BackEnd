package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.StartRouteResource;

public class StartRouteCommandFromResourceAssembler {
    public static StartRouteCommand toCommandFromResource(StartRouteResource resource) {
        return new StartRouteCommand(
                resource.vehicleId(),
                resource.driverId()
        );
    }
}