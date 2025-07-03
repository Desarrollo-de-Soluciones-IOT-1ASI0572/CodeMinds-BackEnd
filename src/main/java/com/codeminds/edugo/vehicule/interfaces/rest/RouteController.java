package com.codeminds.edugo.vehicule.interfaces.rest;

import com.codeminds.edugo.vehicule.application.internal.commandservices.TrackingCommandServiceImpl;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.VehicleResource;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.StartRouteResource;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.EndRouteResource;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.StartRouteCommandFromResourceAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.EndRouteCommandFromResourceAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/routes")
@Tag(name = "Route Management", description = "Endpoints for managing vehicle routes and route operations")
public class RouteController {

    private final TrackingCommandServiceImpl commandService;

    public RouteController(TrackingCommandServiceImpl commandService) {
        this.commandService = commandService;
    }

    /**
     * Marca el inicio de una ruta para un vehículo.
     */
    @PostMapping("/start")
    public ResponseEntity<VehicleResource> startRoute(@RequestBody StartRouteResource resource) {
        return commandService.handle(StartRouteCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(vehicle -> new ResponseEntity<>(
                        VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                        CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Finaliza la ruta activa de un vehículo.
     */
    @PostMapping("/end")
    public ResponseEntity<Void> endRoute(@RequestBody EndRouteResource resource) {
        commandService.handle(EndRouteCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntity.ok().build();
    }
}