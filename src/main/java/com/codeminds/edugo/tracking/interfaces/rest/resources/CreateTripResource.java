package com.codeminds.edugo.tracking.interfaces.rest.resources;

public record CreateTripResource(
        Long vehicleId,
        Long driverId,
        String origin,
        String destination
) {}
