package com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources;

public record CreateRealTimeNotificationResource(String eventType, String description, String userType, Long userId) {
}