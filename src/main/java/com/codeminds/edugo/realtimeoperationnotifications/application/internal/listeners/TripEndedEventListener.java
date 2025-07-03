package com.codeminds.edugo.realtimeoperationnotifications.application.internal.listeners;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.TripEndedEvent;
import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripEndedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;
    private final TripStudentRepository tripStudentRepository;

    public TripEndedEventListener(
            RealTimeNotificationCommandService notificationCommandService,
            TripStudentRepository tripStudentRepository
    ) {
        this.notificationCommandService = notificationCommandService;
        this.tripStudentRepository = tripStudentRepository;
    }

    @EventListener
    public void onTripEnded(TripEndedEvent event) {
        // 1. Notificar al conductor
        notifyDriver(event);

        // 2. Notificar a los padres
        //notifyParents(event);
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

    private void notifyParents(TripEndedEvent event) {
        List<TripStudent> tripStudents = tripStudentRepository.findByTrip_Id(event.tripId());

        tripStudents.forEach(tripStudent -> {
            if (tripStudent.getStudent() != null && tripStudent.getStudent().getParentProfile() != null) {
                Long parentId = tripStudent.getStudent().getParentProfile().getId();
                String studentName = tripStudent.getStudent().getName();

                String description = studentName + " ha completado el viaje #" + event.tripId();

                var command = new CreateRealTimeNotificationCommand(
                        "arrived",
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