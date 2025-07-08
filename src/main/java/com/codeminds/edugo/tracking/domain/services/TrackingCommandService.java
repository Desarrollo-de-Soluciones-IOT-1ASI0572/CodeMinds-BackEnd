package com.codeminds.edugo.tracking.domain.services;

import com.codeminds.edugo.tracking.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.tracking.domain.model.commands.*;
import com.codeminds.edugo.tracking.domain.model.entities.Location;
import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;

import java.util.Optional;

public interface TrackingCommandService {
    Optional<Vehicle> handle(StartRouteCommand command);

    Optional<Location> handle(CreateLocationCommand command);

    void handle(EndRouteCommand command);

    void handle(RegisterStudentBoardingCommand command);

    void handle(RegisterStudentExitCommand command);

    Optional<Vehicle> handle(CreateVehicleCommand command);

    Optional<TripStudent> handle(CreateTripStudentCommand command);

    boolean handle(DeleteTripCommand command);

    Optional<Trip> handle(UpdateTripStatusCommand command);

}