package com.codeminds.edugo.vehicule.interfaces.rest;

import com.codeminds.edugo.vehicule.application.internal.commandservices.ReportSpeedCommandHandler;
import com.codeminds.edugo.vehicule.application.internal.commandservices.StartRouteCommandHandler;
import com.codeminds.edugo.vehicule.application.internal.commandservices.UpdateLocationCommandHandler;
import com.codeminds.edugo.vehicule.application.internal.queryservices.GetCurrentLocationQueryHandler;
import com.codeminds.edugo.vehicule.application.internal.queryservices.ViewRouteHistoryQueryHandler;
import com.codeminds.edugo.vehicule.domain.model.commands.ReportSpeedCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.queries.GetCurrentLocationQuery;
import com.codeminds.edugo.vehicule.domain.model.queries.ViewRouteHistoryQuery;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.*;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.LocationResourceFromEntityAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.StartRouteCommandFromResourceAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.UpdateLocationCommandFromResourceAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/vehicle-tracking")
@Tag(name = "Vehicle Tracking", description = "Endpoints for managing vehicle tracking")
public class VehicleTrackingController {
    private final StartRouteCommandHandler startRouteHandler;
    private final UpdateLocationCommandHandler updateLocationHandler;
    private final GetCurrentLocationQueryHandler getCurrentLocationHandler;
    private final ViewRouteHistoryQueryHandler viewRouteHistoryHandler;
    private final ReportSpeedCommandHandler reportSpeedHandler;

    public VehicleTrackingController(StartRouteCommandHandler startRouteHandler,
                                     UpdateLocationCommandHandler updateLocationHandler,
                                     GetCurrentLocationQueryHandler getCurrentLocationHandler,
                                     ViewRouteHistoryQueryHandler viewRouteHistoryHandler,
                                     ReportSpeedCommandHandler reportSpeedHandler) {
        this.startRouteHandler = startRouteHandler;
        this.updateLocationHandler = updateLocationHandler;
        this.getCurrentLocationHandler = getCurrentLocationHandler;
        this.viewRouteHistoryHandler = viewRouteHistoryHandler;
        this.reportSpeedHandler = reportSpeedHandler;
    }

    @PostMapping("/routes/start")
    public ResponseEntity<VehicleResource> startRoute(@RequestBody StartRouteResource resource) {
        return startRouteHandler.handle(
                        StartRouteCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(vehicle -> new ResponseEntity<>(
                        VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                        CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResource> updateLocation(@RequestBody UpdateLocationResource resource) {
        return updateLocationHandler.handle(
                        UpdateLocationCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(location -> new ResponseEntity<>(
                        LocationResourceFromEntityAssembler.toResourceFromEntity(location),
                        CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/vehicles/{vehicleId}/current-location")
    public ResponseEntity<LocationResource> getCurrentLocation(@PathVariable int vehicleId) {
        return getCurrentLocationHandler.handle(new GetCurrentLocationQuery(vehicleId))
                .map(location -> ResponseEntity.ok(
                        LocationResourceFromEntityAssembler.toResourceFromEntity(location)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicles/{vehicleId}/route-history")
    public ResponseEntity<List<LocationResource>> getRouteHistory(
            @PathVariable int vehicleId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        List<Location> locations = viewRouteHistoryHandler.handle(
                new ViewRouteHistoryQuery(vehicleId, start, end));

        List<LocationResource> resources = locations.stream()
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/vehicles/{vehicleId}/speed-violations")
    public ResponseEntity<Boolean> checkSpeedViolation(
            @PathVariable int vehicleId,
            @RequestParam double speedLimit) {

        return getCurrentLocationHandler.handle(new GetCurrentLocationQuery(vehicleId))
                .map(location -> ResponseEntity.ok(location.isSpeedLimitExceeded(speedLimit)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/speed-reports")
    public ResponseEntity<Boolean> reportSpeed(@RequestBody ReportSpeedResource resource) {
        return reportSpeedHandler.handle(
                        new ReportSpeedCommand(resource.vehicleId(), resource.speedLimit()))
                .map(isViolation -> ResponseEntity.ok(isViolation))
                .orElse(ResponseEntity.notFound().build());
    }
}
