package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.entities.Driver;
import com.codeminds.edugo.profiles.interfaces.rest.resources.DriverResource;

public class DriverResourceFromEntityAssembler {
    public static DriverResource toResourceFromEntity(Driver driver) {
        return new DriverResource(
                driver.getId(),
                driver.getUserId(),
                driver.getDni(),
                driver.getPlateNumber(),
                driver.getVehicleBrand(),
                driver.getVehicleModel(),
                driver.getLicenseNumber(),
                driver.getInsuranceNumber());
    }
}
