package com.codeminds.edugo.vehicule.domain.events;

import java.time.LocalDateTime;

public record SpeedExceededEvent(
        Long vehicleId,
        Double speed,
        Double latitude,
        Double longitude,
        LocalDateTime timestamp
) {}