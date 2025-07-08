package com.codeminds.edugo.tracking.domain.model.commands;

import com.codeminds.edugo.tracking.domain.model.valueobjects.TripStatus;

public record UpdateTripStatusCommand(Long tripId, TripStatus status) {
    public UpdateTripStatusCommand {
        if (tripId == null || tripId <= 0) {
            throw new IllegalArgumentException("Trip ID must be a positive number");
        }
        if (status == null) {
            throw new IllegalArgumentException("Trip status cannot be null");
        }
    }
}
