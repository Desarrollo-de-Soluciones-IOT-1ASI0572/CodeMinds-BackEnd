package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.VehicleResource;

import java.util.Collections;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(
                entity.getVehicleId(),
                entity.getDriverId(),
                entity.getCapacity(),
                entity.getStudentId() != null
                        ? Collections.singletonList(entity.getStudentId())
                        : Collections.emptyList()
        );
    }
}