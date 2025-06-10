package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record DriverResource(Long id, Long user_id, String dni, String plateNumber, String vehicleBrand,
        String vehicleModel, String licenseNumber, String insuranceNumber) {
}
