package com.codeminds.edugo.tracking.domain.model.commands;

import java.time.LocalDateTime;

public record EndRouteCommand(
        Long tripId,
        LocalDateTime endTime
) {}