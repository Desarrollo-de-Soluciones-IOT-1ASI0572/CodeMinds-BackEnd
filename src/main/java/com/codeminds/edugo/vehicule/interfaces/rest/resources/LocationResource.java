package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record LocationResource(
        Integer id,
        Integer vehicleId,
        Double latitude,
        Double longitude,
        Double speed,
        String timestamp
) {}