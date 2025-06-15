package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByVehicleId(Long vehicleId);

    @Query("SELECT l FROM Location l WHERE l.vehicleId = :vehicleId ORDER BY l.timestamp DESC")
    List<Location> findByVehicleIdOrderByTimestampDesc(@Param("vehicleId") Long vehicleId, Pageable pageable);

    default Optional<Location> findLastLocation(Long vehicleId) {
        List<Location> results = findByVehicleIdOrderByTimestampDesc(vehicleId, Pageable.ofSize(1));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    List<Location> findByVehicleIdAndTimestampBetweenOrderByTimestampAsc(
            Long vehicleId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Location> findByTripIdOrderByTimestampAsc(Long tripId);

    List<Location> findByTrip_IdOrderByTimestampAsc(Long tripId);
}
