package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record VehicleResource(
        Long id,
        Integer driverId,
        Integer capacity,
        String status
) { }