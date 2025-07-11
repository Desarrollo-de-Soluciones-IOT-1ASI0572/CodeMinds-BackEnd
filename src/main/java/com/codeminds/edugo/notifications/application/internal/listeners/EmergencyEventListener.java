package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.tracking.domain.events.EmergencyEvent;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmergencyEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;
    private final TripStudentRepository tripStudentRepository;

    public EmergencyEventListener(
            RealTimeNotificationCommandService notificationCommandService,
            TripStudentRepository tripStudentRepository
    ) {
        this.notificationCommandService = notificationCommandService;
        this.tripStudentRepository = tripStudentRepository;
    }

    @EventListener
    public void onEmergency(EmergencyEvent event) {
        System.out.println("🚨 EmergencyEventListener activado");

        // Notificar a cada padre asociado al viaje
        List<TripStudent> tripStudents = tripStudentRepository.findByTrip_Id(event.tripId());

        tripStudents.forEach(tripStudent -> {
            if (tripStudent.getStudent() != null) {
                Long parentId = tripStudent.getStudent().getParentProfileId();
                String studentName = tripStudent.getStudent().getName();

                String description = "⚠️ Emergencia: El viaje de " + studentName + " tiene una situación crítica.";

                var command = new CreateRealTimeNotificationCommand(
                        "emergency",
                        description,
                        "ROLE_PARENT",
                        parentId,
                        event.tripId(),
                        tripStudent.getStudent().getId()
                );

                notificationCommandService.handle(command);
            }
        });

        // Notificar a central/admin
        String adminDescription = "Emergencia activada en viaje #" + event.tripId()
                + " por el conductor #" + event.driverId();
        notificationCommandService.handle(new CreateRealTimeNotificationCommand(
                "emergency",
                adminDescription,
                "ROLE_ADMIN",
                null,
                event.tripId(),
                null
        ));
    }
}