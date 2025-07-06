package com.codeminds.edugo.tracking.domain.model.commands;

import java.time.LocalDateTime;

public record CreateLocationCommand(
        Long vehicleId,
        Long tripId,
        double latitude,
        double longitude,
        double speed,
        LocalDateTime timestamp
) {}
