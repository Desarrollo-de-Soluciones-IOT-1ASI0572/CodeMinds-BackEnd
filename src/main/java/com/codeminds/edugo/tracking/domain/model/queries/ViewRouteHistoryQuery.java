package com.codeminds.edugo.tracking.domain.model.queries;

import java.time.LocalDateTime;

public record ViewRouteHistoryQuery(Long vehicleId, LocalDateTime startDate, LocalDateTime endDate) {
}