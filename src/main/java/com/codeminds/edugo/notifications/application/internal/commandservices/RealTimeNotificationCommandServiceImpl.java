package com.codeminds.edugo.notifications.application.internal.commandservices;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.notifications.infrastructure.persistence.jpa.repositories.RealTimeNotificationRepository;
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
                command.userId(),
                command.tripId(),
                command.studentId()
        );

        var createdRealTimeNotification = realTimeNotificationRepository.save(realTimeNotification);

        return Optional.of(createdRealTimeNotification);
    }
}
