package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.tracking.domain.events.TripStartedEvent;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripStartedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;
    private final TripStudentRepository tripStudentRepository;

    public TripStartedEventListener(
            RealTimeNotificationCommandService notificationCommandService,
            TripStudentRepository tripStudentRepository
    ) {
        this.notificationCommandService = notificationCommandService;
        this.tripStudentRepository = tripStudentRepository;
    }

    @EventListener
    public void onTripStarted(TripStartedEvent event) {
        // 1. Notificar al conductor
        notifyDriver(event);

        // 2. Notificar a los padres
        notifyParents(event);
    }

    private void notifyDriver(TripStartedEvent event) {
        String description = "Viaje iniciado: " + event.origin() + " → " + event.destination();

        var command = new CreateRealTimeNotificationCommand(
                "started",
                description,
                "ROLE_DRIVER",
                event.driverId(),
                event.tripId(),
                null
        );
        notificationCommandService.handle(command);
    }

    private void notifyParents(TripStartedEvent event) {
        List<TripStudent> tripStudents = tripStudentRepository.findByTrip_Id(event.tripId());

        tripStudents.forEach(tripStudent -> {
            if (tripStudent.getStudent() != null && tripStudent.getStudent().getParentProfile() != null) {
                Long parentId = tripStudent.getStudent().getParentProfile().getId();
                String studentName = tripStudent.getStudent().getName();

                String description = "El viaje de " + studentName + " ha comenzado (" +
                        event.origin() + " → " + event.destination() + ")";

                var command = new CreateRealTimeNotificationCommand(
                        "started",
                        description,
                        "ROLE_PARENT",
                        parentId,
                        event.tripId(),
                        tripStudent.getStudent().getId()
                );
                notificationCommandService.handle(command);
            }
        });
    }
}