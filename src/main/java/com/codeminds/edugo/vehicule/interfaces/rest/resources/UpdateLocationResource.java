package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record UpdateLocationResource(
        int vehicleId,
        double latitude,
        double longitude,
        double speed
) {
}