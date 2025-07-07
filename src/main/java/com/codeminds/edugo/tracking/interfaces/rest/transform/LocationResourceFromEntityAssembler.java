package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.entities.Location;
import com.codeminds.edugo.tracking.interfaces.rest.resources.LocationResource;

public class LocationResourceFromEntityAssembler {
    public static LocationResource toResourceFromEntity(Location entity) {
        return new LocationResource(entity.getId(), entity.getVehicleId(), entity.getLatitude(), entity.getLongitude(), entity.getSpeed());
    }
}