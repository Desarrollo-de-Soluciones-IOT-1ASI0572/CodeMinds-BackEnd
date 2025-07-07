package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.StartRouteCommand;

public class StartRouteCommandFromResourceAssembler {
    public static StartRouteCommand toCommandFromResource(Long tripId) {
        return new StartRouteCommand(tripId);
    }
}
