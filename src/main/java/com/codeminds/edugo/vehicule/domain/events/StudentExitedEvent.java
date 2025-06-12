package com.codeminds.edugo.vehicule.domain.events;

import java.time.LocalDateTime;

public record StudentExitedEvent(
        Long studentId,
        Long tripId,
        LocalDateTime exitedAt
) {}
