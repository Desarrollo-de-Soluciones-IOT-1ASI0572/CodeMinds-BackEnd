package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateLocationResource(
        Long vehicleId,
        Long tripId,
        double latitude,
        double longitude,
        double speed,
        LocalDateTime timestamp
) {}
