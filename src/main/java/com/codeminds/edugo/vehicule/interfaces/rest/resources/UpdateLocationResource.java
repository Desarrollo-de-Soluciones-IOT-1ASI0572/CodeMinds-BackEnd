package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateLocationResource(
        Long vehicleId,
        double latitude,
        double longitude,
        double speed,
        LocalDateTime time
) {
}