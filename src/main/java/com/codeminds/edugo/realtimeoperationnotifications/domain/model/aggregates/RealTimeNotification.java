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

    @Embedded
    private NotificationEvent event;

    public RealTimeNotification(String eventType, String description, String userType, Long userId) {
        this.timestamp = LocalDateTime.now();
        this.event = new NotificationEvent(eventType, description);
        this.message = generateMessage(eventType, description);
        this.status = NotificationStatus.PENDING;
        this.userType = userType;
        this.userId = userId;
    }
    public RealTimeNotification() {}

    private String generateMessage(String eventType, String description) {

        String time = timestamp != null ? timestamp.toLocalTime().withNano(0).toString() : "Unknown time";

        switch (eventType) {
            case "boarded":
                return description + " subió al vehículo. " + time;
            case "arrived":
                return description + " llegó al destino. " + time;
            case "speeding":
                return "El vehículo está excediendo la velocidad. " + time;
            case "change":
                return "El vehículo cambió de ruta. " + time;
            default:
                return "Notificación generada: " + description + " a las " + time;
        }
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public NotificationEvent getEvent() {
        return event;
    }

    public String getUserType() { return userType; }

    public Long getUserId() { return userId; }
}
