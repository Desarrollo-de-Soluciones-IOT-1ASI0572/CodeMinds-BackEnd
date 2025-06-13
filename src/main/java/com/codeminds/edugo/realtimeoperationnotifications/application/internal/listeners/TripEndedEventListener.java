package com.codeminds.edugo.realtimeoperationnotifications.application.internal.listeners;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.TripEndedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TripEndedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public TripEndedEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onTripEnded(TripEndedEvent event) {
        String description = "El viaje con ID " + event.tripId() + " ha finalizado";

        var command = new CreateRealTimeNotificationCommand(
                "arrived",                // Tipo de evento (coincide con lógica de message)
                description,              // Descripción
                "DRIVER",                 // Usuario receptor
                event.vehicleId(),        // ID del conductor (si lo tienes desde vehicle, se puede ajustar)
                event.tripId(),           // ID del viaje
                null                      // No hay estudiante asociado
        );

        notificationCommandService.handle(command);
    }
}
