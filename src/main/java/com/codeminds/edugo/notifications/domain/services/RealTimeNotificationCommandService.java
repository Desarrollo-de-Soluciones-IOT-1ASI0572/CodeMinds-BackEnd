package com.codeminds.edugo.notifications.domain.services;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;

import java.util.Optional;

public interface RealTimeNotificationCommandService {
    Optional<RealTimeNotification> handle(CreateRealTimeNotificationCommand command);
}
