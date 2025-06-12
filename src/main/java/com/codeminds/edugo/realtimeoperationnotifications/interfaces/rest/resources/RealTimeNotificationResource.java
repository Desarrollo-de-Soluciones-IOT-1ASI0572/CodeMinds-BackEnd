package com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources;

public record RealTimeNotificationResource(Long id, String message, String status, String userType, Long userId) {
}
