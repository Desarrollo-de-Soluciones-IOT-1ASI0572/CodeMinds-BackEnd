package com.codeminds.edugo.tracking.domain.model.entities;

import com.codeminds.edugo.assignment.domain.models.aggregates.Student;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "trip_students",
        uniqueConstraints = @UniqueConstraint(columnNames = {"trip_id", "student_id"})
)
public class TripStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "attended", nullable = false)
    private boolean attended = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    @Setter
    private Trip trip;

    @Column(name = "boarded_at")
    private LocalDateTime boardedAt;

    @Column(name = "exited_at")
    private LocalDateTime exitedAt;

    public TripStudent() {
    }

    public TripStudent(Student student) {
        this.student = student;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void markAttendance(LocalDateTime boardedAt) {
        this.attended = true;
        this.boardedAt = boardedAt;
    }

    public void markExit(LocalDateTime exitedAt) {
        this.exitedAt = exitedAt;
    }

    // === GETTERS ===
    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isAttended() {
        return attended;
    }

    public LocalDateTime getBoardedAt() {
        return boardedAt;
    }

    public LocalDateTime getExitedAt() {
        return exitedAt;
    }

    public Trip getTrip() {
        return this.trip;
    }
}
