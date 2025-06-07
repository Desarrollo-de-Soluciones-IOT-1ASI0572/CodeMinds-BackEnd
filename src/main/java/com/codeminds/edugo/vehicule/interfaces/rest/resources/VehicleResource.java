package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.util.List;

public record VehicleResource(
        Integer vehicleId,
        Integer driverId,
        Integer capacity,
        List<Integer> studentIds
) {}