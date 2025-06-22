package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record CreateTripResource(
        Long vehicleId,
        Long driverId,
        String origin,
        String destination
) {}
