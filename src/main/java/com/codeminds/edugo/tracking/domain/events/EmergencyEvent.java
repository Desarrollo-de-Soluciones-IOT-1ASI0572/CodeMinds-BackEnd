package com.codeminds.edugo.tracking.domain.events;

public record EmergencyEvent(
        Long tripId,
        Long driverId,

        Double latitude,
        Double longitude
) {}
