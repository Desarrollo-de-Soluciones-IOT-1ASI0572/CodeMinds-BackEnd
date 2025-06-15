package com.codeminds.edugo.vehicule.domain.services;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.*;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;

import java.util.Optional;

public interface TrackingCommandService {
    Optional<Vehicle> handle(StartRouteCommand command);

    Optional<Location> handle(CreateLocationCommand command);

    void handle(EndRouteCommand command);

    void handle(RegisterStudentBoardingCommand command);

    void handle(RegisterStudentExitCommand command);

    Optional<Vehicle> handle(CreateVehicleCommand command);

    Optional<TripStudent> handle(CreateTripStudentCommand command);

    Optional<Location> getCurrentLocation(Long vehicleId);

}