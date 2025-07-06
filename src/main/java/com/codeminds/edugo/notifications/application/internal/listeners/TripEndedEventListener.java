package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.tracking.domain.events.TripEndedEvent;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TripEndedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;
    public TripEndedEventListener(
            RealTimeNotificationCommandService notificationCommandService,
            TripStudentRepository tripStudentRepository
    ) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onTripEnded(TripEndedEvent event) {
        notifyDriver(event);
    }

    private void notifyDriver(TripEndedEvent event) {
        String description = "Viaje finalizado #" + event.tripId();

        var command = new CreateRealTimeNotificationCommand(
                "arrived",
                description,
                "ROLE_DRIVER",
                event.driverId(),
                event.tripId(),
                null
        );
        notificationCommandService.handle(command);
    }
}