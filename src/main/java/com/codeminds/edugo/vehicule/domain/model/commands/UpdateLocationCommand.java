package com.codeminds.edugo.vehicule.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateLocationCommand(
        Long vehicleId,
        double latitude,
        double longitude,
        double speed,
        LocalDateTime timestamp
) {}


//public record UpdateLocationCommand(int vehicleId, double latitude, double longitude, double speed) {}
