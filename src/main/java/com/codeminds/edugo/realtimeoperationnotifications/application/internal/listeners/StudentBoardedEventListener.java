package com.codeminds.edugo.realtimeoperationnotifications.application.internal.listeners;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
//import com.codeminds.edugo.vehicule.domain.events.StudentBoardedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentBoardedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    public StudentBoardedEventListener(RealTimeNotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    /*@EventListener
    public void onStudentBoarded(StudentBoardedEvent event) {
        Long studentId = event.studentId();
        Long tripId = event.tripId();
        Long parentId = findParentIdByStudentId(studentId);

        if (parentId == null) return;

        String description = "El estudiante #" + studentId + " abordó el vehículo.";

        var command = new CreateRealTimeNotificationCommand(
                "boarded",
                description,
                "PARENT",
                parentId,
                studentId,
                tripId
        );

        notificationCommandService.handle(command);
    }*/

    /**
     * Simula la obtención del padre a partir del estudiante.
     * Este método debería consultarse desde una base de datos o servicio en producción.
     */
    private Long findParentIdByStudentId(Long studentId) {
        // TODO: Integrar con tu repositorio o servicio real
        return studentId + 1000; // Simulación temporal
    }
}
