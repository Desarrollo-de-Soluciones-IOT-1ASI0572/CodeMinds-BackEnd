package com.codeminds.edugo.notifications.interfaces.rest.resources;

public record CreateRealTimeNotificationResource(
        String eventType,
        String description,
        String userType,
        Long userId,
        Long tripId,
        Long studentId
) {}