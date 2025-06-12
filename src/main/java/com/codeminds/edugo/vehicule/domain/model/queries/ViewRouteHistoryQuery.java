package com.codeminds.edugo.vehicule.domain.model.queries;

import java.time.LocalDateTime;

public record ViewRouteHistoryQuery(Long vehicleId, LocalDateTime startDate, LocalDateTime endDate) {
}