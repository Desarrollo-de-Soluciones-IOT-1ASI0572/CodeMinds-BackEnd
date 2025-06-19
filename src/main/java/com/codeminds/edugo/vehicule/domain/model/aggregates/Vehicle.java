package com.codeminds.edugo.vehicule.domain.model.aggregates;

import com.codeminds.edugo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import com.codeminds.edugo.vehicule.domain.model.valueobjects.VehicleStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", nullable = false)
    private Integer driverId;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status = VehicleStatus.STOPPED;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips = new ArrayList<>();

    protected Vehicle() {
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Vehicle(Integer driverId, Integer capacity) {
        this.driverId = driverId;
        this.capacity = capacity;
        this.status = VehicleStatus.STOPPED;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public void updateCapacity(Integer newCapacity) {
        if (newCapacity < 0)
            throw new IllegalArgumentException("Capacity cannot be negative");
        this.capacity = newCapacity;
        this.setUpdatedAt();
    }

    public void updateStatus(VehicleStatus newStatus) {
        this.status = newStatus;
        this.setUpdatedAt();
    }


    // Getters manuales
    public Long getId() {
        return id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void startMoving() {
        this.status = VehicleStatus.MOVING;
        this.setUpdatedAt();
    }

    public void stopMoving() {
        this.status = VehicleStatus.STOPPED;
        this.setUpdatedAt();
    }


}