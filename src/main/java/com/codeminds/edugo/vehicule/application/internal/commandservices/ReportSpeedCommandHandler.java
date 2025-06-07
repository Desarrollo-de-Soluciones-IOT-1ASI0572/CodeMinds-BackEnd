package com.codeminds.edugo.vehicule.application.internal.commandservices;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.ReportSpeedCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.UpdateLocationCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.services.TrackingCommandService;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.LocationRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ReportSpeedCommandHandler implements TrackingCommandService {
    private final LocationRepository locationRepository;

    public ReportSpeedCommandHandler(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<Boolean> handle(ReportSpeedCommand command) {
        return locationRepository.findLastLocation(command.vehicleId())
                .map(location -> {
                    boolean isViolation = location.isSpeedLimitExceeded(command.speedLimit());
                    if (isViolation) {
                        System.out.println("Speed violation detected for vehicle: " + command.vehicleId());
                    }
                    return isViolation;
                });
    }

    @Override
    public Optional<Vehicle> handle(StartRouteCommand command) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Optional<Location> handle(UpdateLocationCommand command) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}