package com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //@Query("SELECT t FROM Trip t WHERE t.endTime IS NULL")
    //Optional<Trip> findActiveTripByDriverId(@Param("driverId") Long driverId);

    @Query("SELECT t FROM Trip t WHERE t.driver.id = :driverId AND t.endTime IS NULL")
    Optional<Trip> findActiveTripByDriverId(@Param("driverId") Long driverId);
}
