package com.codeminds.edugo.vehicule.domain.model.commands;

public record ReportSpeedCommand(int vehicleId, double speedLimit) {
}