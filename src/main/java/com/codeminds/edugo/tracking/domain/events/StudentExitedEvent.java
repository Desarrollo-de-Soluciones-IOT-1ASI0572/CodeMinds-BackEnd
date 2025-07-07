package com.codeminds.edugo.tracking.domain.events;

import java.time.LocalDateTime;

public record StudentExitedEvent(
        Long studentId,
        Long tripId,
        LocalDateTime exitedAt
) {}
