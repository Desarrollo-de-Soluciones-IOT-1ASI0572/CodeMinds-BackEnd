package com.codeminds.edugo.tracking.domain.events;

import java.time.LocalDateTime;

public record RouteDeviationDetectedEvent(
        Long vehicleId,
        Double latitude,
        Double longitude,
        String expectedRouteName,
        String deviationDetails,
        LocalDateTime timestamp
) {}
