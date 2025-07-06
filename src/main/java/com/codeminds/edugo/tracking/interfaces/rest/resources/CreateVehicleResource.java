package com.codeminds.edugo.tracking.interfaces.rest.resources;

public record CreateVehicleResource(
        Integer driverId,
        Integer capacity
) {}