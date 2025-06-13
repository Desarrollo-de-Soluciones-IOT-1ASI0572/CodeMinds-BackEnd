package com.codeminds.edugo.vehicule.domain.events;

import java.time.LocalDateTime;

public record StudentBoardedEvent(
        Long studentId,
        Long tripId,
        LocalDateTime boardedAt
) {}
