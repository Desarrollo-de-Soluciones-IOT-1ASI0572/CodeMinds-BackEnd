package com.codeminds.edugo.vehicule.domain.events;

import java.time.LocalDateTime;

public record TripStartedEvent(
        Long tripId,
        Long vehicleId,
        Long driverId,
        String origin,
        String destination,
        LocalDateTime startTime
) {}
