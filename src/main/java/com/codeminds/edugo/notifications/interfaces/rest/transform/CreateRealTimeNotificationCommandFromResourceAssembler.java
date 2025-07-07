package com.codeminds.edugo.notifications.interfaces.rest.transform;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.interfaces.rest.resources.CreateRealTimeNotificationResource;

public class CreateRealTimeNotificationCommandFromResourceAssembler {
    public static CreateRealTimeNotificationCommand toCommandFromResource(CreateRealTimeNotificationResource resource) {
        return new CreateRealTimeNotificationCommand(
                resource.eventType(),
                resource.description(),
                resource.userType(),
                resource.userId(),
                resource.tripId(),
                resource.studentId()
        );
    }
}