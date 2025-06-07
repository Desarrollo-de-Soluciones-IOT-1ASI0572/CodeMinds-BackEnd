package com.codeminds.edugo.vehicule.domain.services;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.ReportSpeedCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.UpdateLocationCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;

import java.util.Optional;

public interface TrackingCommandService {
    Optional<Vehicle> handle(StartRouteCommand command);
    Optional<Location> handle(UpdateLocationCommand command);
    Optional<Boolean> handle(ReportSpeedCommand command);
}