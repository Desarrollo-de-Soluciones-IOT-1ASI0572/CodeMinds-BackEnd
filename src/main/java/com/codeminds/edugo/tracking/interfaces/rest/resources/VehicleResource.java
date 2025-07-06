package com.codeminds.edugo.tracking.interfaces.rest.resources;

public record VehicleResource(
        Long id,
        Integer driverId,
        Integer capacity,
        String status
) { }