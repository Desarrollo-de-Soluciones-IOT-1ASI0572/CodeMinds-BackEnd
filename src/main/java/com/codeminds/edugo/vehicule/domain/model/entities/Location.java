package com.codeminds.edugo.vehicule.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
@Getter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    private Double latitude;
    private Double longitude;
    private Double speed;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime timestamp;

    protected Location() {
        // For JPA
    }

    public Location(Long vehicleId, Double latitude, Double longitude, Double speed) {
        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSpeedLimitExceeded(Double limit) {
        return speed != null && speed > limit;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
