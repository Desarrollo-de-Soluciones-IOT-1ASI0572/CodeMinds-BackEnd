package com.codeminds.edugo.vehicule.domain.events;

import java.time.LocalDateTime;

public record RouteDeviationDetectedEvent(
        Long vehicleId,
        Double latitude,
        Double longitude,
        String expectedRouteName,
        String deviationDetails,
        LocalDateTime timestamp
) {}
