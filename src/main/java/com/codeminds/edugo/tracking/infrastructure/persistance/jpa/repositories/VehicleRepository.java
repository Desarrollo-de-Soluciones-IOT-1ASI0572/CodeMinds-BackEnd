package com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.tracking.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
