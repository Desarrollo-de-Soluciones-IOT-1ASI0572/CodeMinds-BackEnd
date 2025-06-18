package com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.queries.*;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationQueryService;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources.CreateRealTimeNotificationResource;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.resources.RealTimeNotificationResource;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.transform.CreateRealTimeNotificationCommandFromResourceAssembler;
import com.codeminds.edugo.realtimeoperationnotifications.interfaces.rest.transform.RealTimeNotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "RealTimeNotification", description = "Real-Time Notification Management Endpoints")
public class RealTimeNotificationController {
    private final RealTimeNotificationCommandService realTimeNotificationCommandService;
    private final RealTimeNotificationQueryService realTimeNotificationQueryService;

    /**
     * Crea una nueva notificación en tiempo real.
     *
     * @return la notificación creada en formato recurso, o 400 si falla
     */
    public RealTimeNotificationController(RealTimeNotificationCommandService realTimeNotificationCommandService,
                                          RealTimeNotificationQueryService realTimeNotificationQueryService) {
        this.realTimeNotificationCommandService = realTimeNotificationCommandService;
        this.realTimeNotificationQueryService = realTimeNotificationQueryService;
    }

    @PostMapping
    public ResponseEntity<RealTimeNotificationResource> createRealTimeNotification(
            @RequestBody CreateRealTimeNotificationResource resource) {
        Optional<RealTimeNotification> realTimeNotificationResource = realTimeNotificationCommandService
                .handle(CreateRealTimeNotificationCommandFromResourceAssembler.toCommandFromResource(resource));

        return realTimeNotificationResource.flatMap(realTimeNotification ->
                        Optional.of(new ResponseEntity<>(
                                RealTimeNotificationResourceFromEntityAssembler.toResourceFromEntity(realTimeNotification), CREATED)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    /**
     * Obtiene todas las notificaciones registradas en el sistema.
     *
     * @return lista completa de notificaciones existentes
     */
    @GetMapping
    public ResponseEntity<List<RealTimeNotificationResource>> getAllRealTimeNotification() {
        var getAllRealTimeNotifications = new GetAllRealTimeNotificationsQuery();
        var realTimeNotification = realTimeNotificationQueryService.handle(getAllRealTimeNotifications);
        var realTimeNotificationsResources = realTimeNotification.stream().map(
                RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }

    /**
     * Filtra notificaciones por tipo de usuario receptor (ej. "PARENT", "DRIVER").
     *
     * @param notificationsForUserType tipo de usuario
     * @return lista de notificaciones asociadas a ese tipo de usuario
     */
    @GetMapping(value = "/user-type/{notificationsForUserType}")
    public ResponseEntity<List<RealTimeNotificationResource>> getRealTimeNotificationsForUserType(@PathVariable String notificationsForUserType) {
        var getRealTimeNotificationsByUserType = new GetRealNotificationsForUserType(notificationsForUserType);
        var realTimeNotification = realTimeNotificationQueryService.handle(getRealTimeNotificationsByUserType);
        var realTimeNotificationsResources = realTimeNotification.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }

    /**
     * Filtra notificaciones por ID del usuario receptor (padre o conductor).
     *
     * @param notificationsForUserId ID del usuario
     * @return lista de notificaciones dirigidas a ese usuario
     */
    @GetMapping(value = "/user-id/{notificationsForUserId}")
    public ResponseEntity<List<RealTimeNotificationResource>> getRealTimeNotificationsForUserId(@PathVariable Long notificationsForUserId) {
        var getRealTimeNotificationsByUserId = new GetRealNotificationsForUserId(notificationsForUserId);
        var realTimeNotification = realTimeNotificationQueryService.handle(getRealTimeNotificationsByUserId);
        var realTimeNotificationsResources = realTimeNotification.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }

    /**
     * Filtra notificaciones asociadas a un viaje específico.
     *
     * @param tripId ID del viaje
     * @return lista de notificaciones generadas durante ese viaje
     */
    @GetMapping("/trip-id/{tripId}")
    public ResponseEntity<List<RealTimeNotificationResource>> getNotificationsByTripId(@PathVariable Long tripId) {
        var query = new GetRealNotificationsForTripId(tripId);
        var results = realTimeNotificationQueryService.handle(query);
        var resources = results.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Filtra notificaciones generadas por eventos de un estudiante específico.
     *
     * @param studentId ID del estudiante
     * @return lista de notificaciones vinculadas a ese estudiante
     */
    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<List<RealTimeNotificationResource>> getNotificationsByStudentId(@PathVariable Long studentId) {
        var query = new GetRealNotificationsForStudentId(studentId);
        var results = realTimeNotificationQueryService.handle(query);
        var resources = results.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Filtra notificaciones por combinación de usuario y viaje.
     * Útil para mostrar al padre o conductor las notificaciones de un viaje en particular.
     *
     * @param userId ID del receptor (padre o conductor)
     * @param tripId ID del viaje
     * @return lista de notificaciones para ese usuario durante ese viaje
     */
    @GetMapping("/user-id/{userId}/trip-id/{tripId}")
    public ResponseEntity<List<RealTimeNotificationResource>> getNotificationsByUserAndTrip(@PathVariable Long userId, @PathVariable Long tripId) {
        var query = new GetRealNotificationsForUserAndTrip(userId, tripId);
        var results = realTimeNotificationQueryService.handle(query);
        var resources = results.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }
}
