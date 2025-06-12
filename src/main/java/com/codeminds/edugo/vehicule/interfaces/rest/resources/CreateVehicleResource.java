package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record CreateVehicleResource(
        Integer driverId,
        Integer capacity
) {}