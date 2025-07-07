package com.codeminds.edugo.tracking.domain.events;

import java.time.LocalDateTime;

public record SpeedExceededEvent(
        Long vehicleId,
        Double speed,
        Double latitude,
        Double longitude,
        LocalDateTime timestamp
) {}