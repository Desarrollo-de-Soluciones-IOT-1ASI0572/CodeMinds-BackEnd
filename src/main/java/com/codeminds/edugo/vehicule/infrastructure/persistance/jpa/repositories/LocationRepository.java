package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByVehicleId(int vehicleId);

    @Query("SELECT l FROM Location l WHERE l.vehicleId = :vehicleId ORDER BY l.timestamp DESC")
    Optional<Location> findLastLocation(@Param("vehicleId") int vehicleId);
}