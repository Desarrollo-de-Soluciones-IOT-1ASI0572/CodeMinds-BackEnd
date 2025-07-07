package com.codeminds.edugo.notifications.interfaces.rest.resources;

import java.time.LocalDateTime;

public record RealTimeNotificationResource(
        Long id,
        String message,
        String status,
        String userType,
        Long userId,
        String eventType,
        String description,
        LocalDateTime timestamp,
        Long tripId,
        Long studentId
) {}