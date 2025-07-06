package com.codeminds.edugo.notifications.domain.model.commands;

public record CreateRealTimeNotificationCommand(
        String eventType,
        String description,
        String userType,
        Long userId,
        Long tripId,
        Long studentId
) {}
