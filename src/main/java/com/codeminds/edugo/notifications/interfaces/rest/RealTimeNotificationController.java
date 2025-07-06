package com.codeminds.edugo.notifications.interfaces.rest;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.notifications.domain.model.queries.GetAllRealTimeNotificationsQuery;
import com.codeminds.edugo.notifications.domain.model.queries.GetRealNotificationsForUserId;
import com.codeminds.edugo.notifications.domain.model.queries.GetRealNotificationsForUserType;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationCommandService;
import com.codeminds.edugo.notifications.domain.services.RealTimeNotificationQueryService;
import com.codeminds.edugo.notifications.interfaces.rest.resources.CreateRealTimeNotificationResource;
import com.codeminds.edugo.notifications.interfaces.rest.resources.RealTimeNotificationResource;
import com.codeminds.edugo.notifications.interfaces.rest.transform.CreateRealTimeNotificationCommandFromResourceAssembler;
import com.codeminds.edugo.notifications.interfaces.rest.transform.RealTimeNotificationResourceFromEntityAssembler;
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

    @GetMapping
    private ResponseEntity<List<RealTimeNotificationResource>> getAllRealTimeNotification() {
        var getAllRealTimeNotifications = new GetAllRealTimeNotificationsQuery();
        var realTimeNotification = realTimeNotificationQueryService.handle(getAllRealTimeNotifications);
        var realTimeNotificationsResources = realTimeNotification.stream().map(
                RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }

    @GetMapping(value = "/user-type/{notificationsForUserType}")
    public ResponseEntity<List<RealTimeNotificationResource>> getRealTimeNotificationsForUserType(@PathVariable String notificationsForUserType) {
        var getRealTimeNotificationsByUserType = new GetRealNotificationsForUserType(notificationsForUserType);
        var realTimeNotification = realTimeNotificationQueryService.handle(getRealTimeNotificationsByUserType);
        var realTimeNotificationsResources = realTimeNotification.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }

    @GetMapping(value = "/user-id/{notificationsForUserId}")
    public ResponseEntity<List<RealTimeNotificationResource>> getRealTimeNotificationsForUserId(@PathVariable Long notificationsForUserId) {
        var getRealTimeNotificationsByUserId = new GetRealNotificationsForUserId(notificationsForUserId);
        var realTimeNotification = realTimeNotificationQueryService.handle(getRealTimeNotificationsByUserId);
        var realTimeNotificationsResources = realTimeNotification.stream().map(RealTimeNotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(realTimeNotificationsResources);
    }
}
