package com.codeminds.edugo.vehicule.domain.model.entities;

import com.codeminds.edugo.vehicule.domain.model.valueobjects.Coordinates;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;


@Entity
@Table(name = "locations")
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vehicleid")
    private Integer vehicleId;

    private Double latitude;
    private Double longitude;
    private Double speed;

    @Column(name = "created_at")
    private LocalDateTime timestamp;

    protected Location() {

    }

    public Location(Integer vehicleId, Double latitude, Double longitude, Double speed) {
        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public Integer getVehicleId() {
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

    public boolean isSpeedLimitExceeded(Double limit) {
        return speed > limit;
    }
}