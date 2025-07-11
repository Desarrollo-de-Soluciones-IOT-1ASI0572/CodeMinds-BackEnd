package com.codeminds.edugo.tracking.domain.model.commands;

public record ActivateEmergencyCommand(
        Long tripId,
        Double latitude,
        Double longitude
) {}
