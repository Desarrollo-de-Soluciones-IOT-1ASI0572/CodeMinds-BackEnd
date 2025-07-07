package com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import com.codeminds.edugo.tracking.domain.model.valueobjects.TripStatus;
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


    List<Trip> findByEndTimeIsNotNull();

    List<Trip> findByEndTimeIsNotNullAndVehicle_DriverId(Long driverId);

    public List<Trip> findByDriver_IdAndStatusIn(Long driverId, List<TripStatus> statuses);


    //@Query("SELECT t FROM Trip t WHERE t.driver.id = :driverId AND t.endTime IS NULL")
    //Optional<Trip> findActiveTripByDriverId(@Param("driverId") Long driverId);

    List<Trip> findByDriver_IdAndStatus(Long driverId, TripStatus status);
}
