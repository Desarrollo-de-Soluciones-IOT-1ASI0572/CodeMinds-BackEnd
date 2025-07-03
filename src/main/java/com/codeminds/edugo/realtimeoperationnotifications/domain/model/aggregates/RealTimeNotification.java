package com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.valueobjects.NotificationEvent;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.valueobjects.NotificationStatus;
import com.codeminds.edugo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class RealTimeNotification extends AuditableAbstractAggregateRoot<RealTimeNotification> {

    private String message;
    private NotificationStatus status;
    private LocalDateTime timestamp;
    private String userType;
    private Long userId;

    private Long tripId;      // Nuevo: ID del viaje relacionado (opcional)
    private Long studentId;   // Nuevo: ID del estudiante relacionado (opcional)

    @Embedded
    private NotificationEvent event;

    public RealTimeNotification(
            String eventType,
            String description,
            String userType,
            Long userId,
            Long tripId,
            Long studentId
    ) {
        this.timestamp = LocalDateTime.now();
        this.event = new NotificationEvent(eventType, description);
        this.message = generateMessage(eventType, description);
        this.status = NotificationStatus.PENDING;
        this.userType = userType;
        this.userId = userId;
        this.tripId = tripId;
        this.studentId = studentId;
    }

    public RealTimeNotification() {}

    private String generateMessage(String eventType, String description) {
        String time = timestamp != null ? timestamp.toLocalTime().withNano(0).toString() : "hora desconocida";

        return switch (eventType) {
            case "boarded" -> description;
            case "arrived" -> description;
            case "speeding" -> "El vehículo está excediendo la velocidad. " + time;
            case "change" -> "El vehículo cambió de ruta. " + time;
            default -> description + " (" + time + ")";
        };
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }

    // Getters manuales adicionales (opcional, según tu estilo)
    public Long getTripId() {
        return tripId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserType() {
        return userType;
    }

    public Long getUserId() {
        return userId;
    }

    public NotificationEvent getEvent() {
        return event;
    }

}
