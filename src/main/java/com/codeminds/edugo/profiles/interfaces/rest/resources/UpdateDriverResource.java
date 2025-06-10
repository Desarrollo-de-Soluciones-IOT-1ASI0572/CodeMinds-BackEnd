package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record UpdateDriverResource(
        String dni,
        String plateNumber,
        String vehicleBrand,
        String vehicleModel,
        String licenseNumber,
        String insuranceNumber) {

}
