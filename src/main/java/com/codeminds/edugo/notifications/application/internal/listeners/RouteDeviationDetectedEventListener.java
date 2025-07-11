package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.tracking.domain.events.RouteDeviationDetectedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RouteDeviationDetectedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public RouteDeviationDetectedEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onRouteDeviationDetected(RouteDeviationDetectedEvent event) {
        Long vehicleId = event.vehicleId();
        String description = "Desvío detectado: " + event.deviationDetails();

        var command = new CreateRealTimeNotificationCommand(
                "change",               // Tipo de evento
                description,            // Descripción detallada
                "ROLE_DRIVER",               // Usuario receptor: conductor
                vehicleId,              // Se identifica por el vehículo (puede mapearse al driver en el futuro)
                null,                   // No está asociado directamente a un trip
                null                    // No está asociado a un estudiante
        );

        notificationCommandService.handle(command);
    }
}
