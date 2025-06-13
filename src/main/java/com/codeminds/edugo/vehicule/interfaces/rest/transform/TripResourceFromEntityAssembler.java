package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.TripResource;

public class TripResourceFromEntityAssembler {
    public static TripResource toResourceFromEntity(Trip trip) {
        return new TripResource(
                trip.getId(),
                trip.getVehicle().getId(),
                trip.getOrigin(),
                trip.getDestination(),
                trip.getStartTime(),
                trip.getEndTime()
        );
    }
}
