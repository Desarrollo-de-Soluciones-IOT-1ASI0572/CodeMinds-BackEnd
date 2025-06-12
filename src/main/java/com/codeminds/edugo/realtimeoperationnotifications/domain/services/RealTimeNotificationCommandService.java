package com.codeminds.edugo.realtimeoperationnotifications.domain.services;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;

import java.util.Optional;

public interface RealTimeNotificationCommandService {
    Optional<RealTimeNotification> handle(CreateRealTimeNotificationCommand command);
}
