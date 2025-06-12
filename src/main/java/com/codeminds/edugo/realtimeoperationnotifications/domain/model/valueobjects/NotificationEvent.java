package com.codeminds.edugo.realtimeoperationnotifications.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class NotificationEvent {

    private final String eventType;
    private final String description;

    // Constructor sin argumentos requerido por JPA
    public NotificationEvent() {
        this.eventType = null;
        this.description = null;
    }

    // Constructor de validación
    public NotificationEvent(String eventType, String description) {
        if (eventType == null || eventType.isBlank()) {
            throw new IllegalArgumentException("eventType cannot be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }

        this.eventType = eventType;
        this.description = description;
    }
}
