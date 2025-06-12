package com.codeminds.edugo.vehicule.domain.model.commands;

import java.time.LocalDateTime;

public record EndRouteCommand(
        Long tripId,
        LocalDateTime endTime
) {}