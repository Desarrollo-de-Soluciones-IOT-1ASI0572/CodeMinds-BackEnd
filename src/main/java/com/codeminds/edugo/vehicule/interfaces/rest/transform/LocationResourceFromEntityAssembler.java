package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.LocationResource;
import java.time.format.DateTimeFormatter;
public class LocationResourceFromEntityAssembler {
    public static LocationResource toResourceFromEntity(Location entity) {
        return new LocationResource(
                entity.getId(),
                entity.getVehicleId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getSpeed(),
                entity.getTimestamp() != null ?
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(entity.getTimestamp()) :
                        null
        );
    }
}