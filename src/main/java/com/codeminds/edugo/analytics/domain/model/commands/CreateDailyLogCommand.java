package com.codeminds.edugo.analytics.domain.model.commands;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateDailyLogCommand(
        Long driverUserId,
        @Schema(type = "string", example = "2025-06-05")
        LocalDate date,
        @Schema(type = "string", example = "07:30")
        LocalTime arrivalTimeAtSchool,
        @Schema(type = "string", example = "15:45")
        LocalTime returnTimeHome,
        double speed,
        boolean detour,
        boolean lateness,
        boolean speeding
) {
}
