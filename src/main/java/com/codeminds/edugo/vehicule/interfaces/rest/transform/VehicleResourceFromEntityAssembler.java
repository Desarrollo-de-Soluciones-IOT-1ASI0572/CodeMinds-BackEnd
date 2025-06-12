package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        return new VehicleResource(
                vehicle.getId(),
                vehicle.getDriverId(),
                vehicle.getCapacity(),
                vehicle.getStatus() != null ? vehicle.getStatus().name() : null
        );
    }
}