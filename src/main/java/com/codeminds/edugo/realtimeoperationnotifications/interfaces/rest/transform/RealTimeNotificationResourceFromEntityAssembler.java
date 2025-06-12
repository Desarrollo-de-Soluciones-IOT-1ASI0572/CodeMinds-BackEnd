package com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.transform;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources.RealTimeNotificationResource;

public class RealTimeNotificationResourceFromEntityAssembler {
    public static RealTimeNotificationResource toResourceFromEntity(RealTimeNotification entity) {
        return new RealTimeNotificationResource(
                entity.getId(),
                entity.getMessage(),
                entity.getStatus().toString(),
                entity.getUserType(),
                entity.getUserId()
        );
    }
}