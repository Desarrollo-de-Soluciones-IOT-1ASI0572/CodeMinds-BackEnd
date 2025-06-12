package com.codeminds.edugo.vehicule.domain.model.commands;

public record CreateVehicleCommand(
        Integer driverId,
        Integer capacity
) {}