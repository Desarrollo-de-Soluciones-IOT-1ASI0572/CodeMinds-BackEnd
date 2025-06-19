package com.codeminds.edugo.analytics.interfaces.resources;

public record DailyLogResource(
        Long id,
        Long driverUserId,
        String date,
        String arrivalTimeAtSchool,
        String returnTimeHome,
        double speed,
        IncidentResource incident
) {
}
