package com.codeminds.edugo.vehicule.domain.model.entities;

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

    @Column(name = "student_id", nullable = false)
    private Long studentId;

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

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public TripStudent(Long studentId) {
        this.studentId = studentId;
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

    public Long getStudentId() {
        return studentId;
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

}
