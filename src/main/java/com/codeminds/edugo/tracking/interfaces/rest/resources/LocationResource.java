package com.codeminds.edugo.tracking.interfaces.rest.resources;

public record LocationResource(Long id, Long vehicleId, Double latitude, Double longitude, Double speed) {}