package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
