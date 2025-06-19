package com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.transform;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources.CreateRealTimeNotificationResource;

public class CreateRealTimeNotificationCommandFromResourceAssembler {
    public static CreateRealTimeNotificationCommand toCommandFromResource(CreateRealTimeNotificationResource resource) {
        return new CreateRealTimeNotificationCommand(
                resource.eventType(), resource.description(), resource.userType(), resource.userId());
    }
}