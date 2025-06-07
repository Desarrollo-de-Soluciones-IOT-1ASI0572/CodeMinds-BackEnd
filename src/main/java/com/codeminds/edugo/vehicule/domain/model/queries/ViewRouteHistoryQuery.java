package com.codeminds.edugo.vehicule.domain.model.queries;

import java.time.LocalDateTime;

public record ViewRouteHistoryQuery(int vehicleId, LocalDateTime startDate, LocalDateTime endDate) {
}