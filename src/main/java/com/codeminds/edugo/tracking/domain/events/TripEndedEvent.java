package com.codeminds.edugo.tracking.domain.events;

import java.time.LocalDateTime;

public record TripEndedEvent(
        Long tripId,
        Long vehicleId,
        Long driverId,
        String origin,
        String destination,
        LocalDateTime startTime
) {}
