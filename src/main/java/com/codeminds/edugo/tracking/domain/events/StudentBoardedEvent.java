package com.codeminds.edugo.tracking.domain.events;

import java.time.LocalDateTime;

public record StudentBoardedEvent(
        Long studentId,
        Long tripId,
        LocalDateTime boardedAt
) {}
