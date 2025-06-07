package com.codeminds.edugo.vehicule.application.internal.commandservices;

import com.codeminds.edugo.vehicule.domain.model.commands.UpdateLocationCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;

import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateLocationCommandHandler {

    private final LocationRepository locationRepository;

    public UpdateLocationCommandHandler(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public Optional<Location> handle(UpdateLocationCommand command) {
        Location location = new Location(
                command.vehicleId(),
                command.latitude(),
                command.longitude(),
                command.speed()
        );

        return Optional.of(locationRepository.save(location));
    }
}