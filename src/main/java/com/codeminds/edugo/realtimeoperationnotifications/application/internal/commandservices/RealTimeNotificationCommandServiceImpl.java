package com.codeminds.edugo.realtimeoperationnotifications.application.internal.commandservices;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.realtimeoperationnotifications.infrastructure.persistence.jpa.repositories.RealTimeNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RealTimeNotificationCommandServiceImpl implements RealTimeNotificationCommandService {
    private final RealTimeNotificationRepository realTimeNotificationRepository;

    public RealTimeNotificationCommandServiceImpl(RealTimeNotificationRepository realTimeNotificationRepository) {
        this.realTimeNotificationRepository = realTimeNotificationRepository;
    }

    @Override
    public Optional<RealTimeNotification> handle(CreateRealTimeNotificationCommand command) {

        var realTimeNotification = new RealTimeNotification(
                command.eventType(),
                command.description(),
                command.userType(),
                command.userId());

        var createdRealTimeNotification = realTimeNotificationRepository.save(realTimeNotification);

        return Optional.of(createdRealTimeNotification);
    }
}
