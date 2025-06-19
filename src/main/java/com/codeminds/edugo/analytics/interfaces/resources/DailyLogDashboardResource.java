package com.codeminds.edugo.analytics.interfaces.resources;

import java.util.List;

public record DailyLogDashboardResource(
        Long driverUserId,
        String week,
        List<SpeedPerDayResource> speedPerDay,
        List<ArrivalTimeResource> arrivalTimes,
        IncidentSummaryResource incidentSummary
) {
}
