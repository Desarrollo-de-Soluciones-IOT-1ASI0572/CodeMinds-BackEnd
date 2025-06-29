package com.codeminds.edugo.realtimeoperationnotifications.application.internal.listeners;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.TripStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TripStartedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public TripStartedEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onTripStarted(TripStartedEvent event) {
        String description = "Viaje iniciado de " + event.origin() + " a " + event.destination();

        var command = new CreateRealTimeNotificationCommand(
                "started",                 // Tipo de evento
                description,              // Descripción del viaje
                "DRIVER",                 // Tipo de usuario (conductor)
                event.driverId(),         // ID del conductor
                event.tripId(),           // Asociado al viaje
                null                      // No asociado a un estudiante
        );

        notificationCommandService.handle(command);
    }
}
