package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import java.util.Optional;
import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Location, Long> {
    void save(Vehicle vehicle);
    Optional<Vehicle> findById(int vehicleId);
}
