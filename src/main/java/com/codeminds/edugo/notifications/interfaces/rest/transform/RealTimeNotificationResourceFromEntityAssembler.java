package com.codeminds.edugo.notifications.interfaces.rest.transform;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.notifications.interfaces.rest.resources.RealTimeNotificationResource;

public class RealTimeNotificationResourceFromEntityAssembler {
    public static RealTimeNotificationResource toResourceFromEntity(RealTimeNotification entity) {
        return new RealTimeNotificationResource(
                entity.getId(),
                entity.getMessage(),
                entity.getStatus().name(),
                entity.getUserType(),
                entity.getUserId(),
                entity.getEvent().getEventType(),
                entity.getEvent().getDescription(),
                entity.getTimestamp(),
                entity.getTripId(),
                entity.getStudentId()
        );
    }
}