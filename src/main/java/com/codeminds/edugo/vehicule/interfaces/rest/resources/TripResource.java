package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.time.LocalDateTime;

public record TripResource(
        Long id,
        Long vehicleId,
        String origin,
        String destination,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}