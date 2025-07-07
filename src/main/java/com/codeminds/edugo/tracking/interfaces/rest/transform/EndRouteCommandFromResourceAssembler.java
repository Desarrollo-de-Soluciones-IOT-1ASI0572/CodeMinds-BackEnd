package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.EndRouteCommand;

import java.time.LocalDateTime;

public class EndRouteCommandFromResourceAssembler {
    public static EndRouteCommand toCommandFromResource(Long tripId) {
        return new EndRouteCommand(
                tripId,
                LocalDateTime.now()
        );
    }
}
