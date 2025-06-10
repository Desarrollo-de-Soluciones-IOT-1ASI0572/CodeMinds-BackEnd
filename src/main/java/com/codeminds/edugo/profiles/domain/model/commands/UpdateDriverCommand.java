package com.codeminds.edugo.profiles.domain.model.commands;

public record UpdateDriverCommand(Long id, String dni, String plateNumber, String vehicleBrand, String vehicleModel,
        String licenseNumber, String insuranceNumber) {
}
