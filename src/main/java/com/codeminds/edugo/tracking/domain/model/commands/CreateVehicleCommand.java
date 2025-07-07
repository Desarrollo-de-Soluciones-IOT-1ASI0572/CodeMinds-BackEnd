package com.codeminds.edugo.tracking.domain.model.commands;

public record CreateVehicleCommand(
        Integer driverId,
        Integer capacity
) {}