package com.codeminds.edugo.notifications.application.internal.listeners;

import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import com.codeminds.edugo.notifications.domain.model.commands.CreateRealTimeNotificationCommand;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.vehicule.domain.events.StudentBoardedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentBoardedEventListener {

    private final RealTimeNotificationCommandService notificationCommandService;

    private final StudentRepository studentRepository;

    public StudentBoardedEventListener(RealTimeNotificationCommandService notificationCommandService, StudentRepository studentRepository) {
        this.notificationCommandService = notificationCommandService;
        this.studentRepository = studentRepository;
    }

    @EventListener
    public void onStudentBoarded(StudentBoardedEvent event) {
        Long studentId = event.studentId();
        Long tripId = event.tripId();
        Long parentId = findParentIdByStudentId(studentId);

        if (parentId == null) {
            System.out.println("⚠️ No se encontró parentId para studentId: " + studentId);
            return;
        }


        String studentName = getStudentName(studentId);
        String description = studentName + " ha subido al vehículo del viaje #" + tripId;

        var command = new CreateRealTimeNotificationCommand(
                "boarded",       // Tipo de evento
                description,
                "ROLE_PARENT",   // userType estandarizado
                parentId,        // ID del padre
                tripId,          // ID del viaje (correctamente posicionado)
                studentId        // ID del estudiante
        );

        notificationCommandService.handle(command);
    }

    private Long findParentIdByStudentId(Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> student.getParentProfile().getId())  // Cambiado de getParent() a getParentProfile()
                .orElse(null);
    }

    private String getStudentName(Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> student.getName() + " " + student.getLastName())  // Usamos name y lastName directamente
                .orElse("Estudiante #" + studentId);
    }
}
