package com.codeminds.edugo.tracking.domain.model.entities;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.tracking.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.tracking.domain.model.valueobjects.TripStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripStudent> students = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "driver_id", nullable = false)
    private Profile driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status;

    public Trip(Vehicle vehicle, Profile driver, String origin, String destination) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.status = TripStatus.CREATED;
    }

    public Trip() {
        this.status = TripStatus.CREATED;
    }

    public void startTrip() {
        this.status = TripStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

    public void endTrip() {
        this.status = TripStatus.COMPLETED;
        this.endTime = LocalDateTime.now();
    }

    public void updateStatus(TripStatus newStatus) {
        this.status = newStatus;

        if (newStatus == TripStatus.IN_PROGRESS && this.startTime == null) {
            this.startTime = LocalDateTime.now();
        } else if (newStatus == TripStatus.COMPLETED && this.endTime == null) {
            this.endTime = LocalDateTime.now();
        }
    }

    public void addStudent(TripStudent student) {
        this.students.add(student);
        student.setTrip(this);
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        location.setTrip(this);
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public List<TripStudent> getStudents() {
        return students;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Profile getDriver() {
        return driver;
    }
}
