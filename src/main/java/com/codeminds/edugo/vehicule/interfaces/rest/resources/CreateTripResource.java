package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record CreateTripResource(
        Long vehicleId,
        String origin,
        String destination
) {}