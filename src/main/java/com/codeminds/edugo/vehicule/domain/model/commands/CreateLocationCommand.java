package com.codeminds.edugo.vehicule.domain.model.commands;

import java.time.LocalDateTime;

public record CreateLocationCommand(
        Long vehicleId,
        Long tripId,
        double latitude,
        double longitude,
        double speed,
        LocalDateTime timestamp
) {}
