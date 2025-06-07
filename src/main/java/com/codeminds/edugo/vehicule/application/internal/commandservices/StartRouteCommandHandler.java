package com.codeminds.edugo.vehicule.application.internal.commandservices;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.VehiculeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StartRouteCommandHandler {

    private final VehiculeRepository vehicleRepository;

    public StartRouteCommandHandler(VehiculeRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Optional<Vehicle> handle(StartRouteCommand command) {
        Vehicle vehicle = new Vehicle(
                command.vehicleId(),
                command.driverId(),
                10 // Capacidad inicial
        );

        vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }
}
