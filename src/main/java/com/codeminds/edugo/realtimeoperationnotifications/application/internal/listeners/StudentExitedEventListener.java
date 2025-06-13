package com.codeminds.edugo.realtimeoperationnotifications.application.internal.listeners;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.StudentExitedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentExitedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public StudentExitedEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onStudentExited(StudentExitedEvent event) {
        // Por ahora usamos el studentId como userId del padre (ajustar si hay lógica de relación real)
        Long studentId = event.studentId();
        Long tripId = event.tripId();
        Long parentId = studentId; // Por ahora 1:1 (ajustar cuando se modele la relación real)

        String description = "El estudiante con ID " + studentId;

        var command = new CreateRealTimeNotificationCommand(
                "arrived",
                description,
                "PARENT",
                parentId,
                tripId,
                studentId
        );

        notificationCommandService.handle(command);
    }
}