package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.SpeedExceededEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpeedExceededEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public SpeedExceededEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onSpeedExceeded(SpeedExceededEvent event) {
        Long vehicleId = event.vehicleId();
        Double speed = event.speed();

        String description = "Velocidad actual: " + speed + " km/h";

        var command = new CreateRealTimeNotificationCommand(
                "speeding",           // Tipo de evento
                description,          // Detalle del exceso de velocidad
                "ROLE_DRIVER",             // Usuario receptor: conductor
                vehicleId,            // Se usa el vehicleId como ID del conductor (ajustar si se modela driverId)
                null,                 // No aplica tripId directamente
                null                  // No hay estudiante involucrado
        );

        notificationCommandService.handle(command);
    }
}
