package com.codeminds.edugo.vehicule.domain.model.aggregates;

import com.codeminds.edugo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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

    @Column(name = "student_id", nullable = true)
    private Integer studentId;

    protected Vehicle() {
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Vehicle(Integer driverId, Integer capacity, Integer studentId) {
        this.driverId = driverId;
        this.capacity = capacity;
        this.studentId = studentId;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Long getId() {
        return this.id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getVehicleId() {
        return getId() != null ? getId().intValue() : null;
    }

    public void updateCapacity(Integer newCapacity) {
        if (newCapacity < 0)
            throw new IllegalArgumentException("Capacity cannot be negative");
        this.capacity = newCapacity;
        this.setUpdatedAt();
    }

    public void assignStudent(Integer studentId) {
        this.studentId = studentId;
        this.setUpdatedAt();
    }

    public void removeStudent() {
        this.studentId = null;
        this.setUpdatedAt();
    }
}