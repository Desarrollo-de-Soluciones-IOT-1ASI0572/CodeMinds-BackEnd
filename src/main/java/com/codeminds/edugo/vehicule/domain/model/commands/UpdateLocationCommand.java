package com.codeminds.edugo.vehicule.domain.model.commands;

public record UpdateLocationCommand(int vehicleId, double latitude, double longitude, double speed) {
}
