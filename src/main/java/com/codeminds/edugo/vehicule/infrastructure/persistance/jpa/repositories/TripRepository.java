package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findByVehicle_IdAndEndTimeIsNull(Long vehicleId);

    List<Trip> findByVehicle_DriverIdAndEndTimeIsNotNull(Integer driverId);

    Trip findTopByVehicle_IdAndStartTimeIsNullOrderByIdDesc(Long vehicleId);

    List<Trip> findByEndTimeIsNotNull();

    // En TripRepository.java
    List<Trip> findByEndTimeIsNotNullAndVehicle_DriverId(Long driverId);
}
