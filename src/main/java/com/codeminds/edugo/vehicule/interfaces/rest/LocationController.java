package com.codeminds.edugo.vehicule.interfaces.rest;

import com.codeminds.edugo.vehicule.application.internal.commandservices.TrackingCommandServiceImpl;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.LocationRepository;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.LocationResource;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.CreateLocationResource;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.CreateLocationCommandFromResourceAssembler;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.LocationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/locations")
@Tag(name = "Location Management",
        description = "Endpoints for real-time vehicle location tracking and location history management")
public class LocationController {

    private final TrackingCommandServiceImpl commandService;
    private final LocationRepository locationRepository;

    public LocationController(TrackingCommandServiceImpl commandService,
                              LocationRepository locationRepository) {
        this.commandService = commandService;
        this.locationRepository = locationRepository;
    }

    /**
     * Actualiza la ubicación de un vehículo en tiempo real.
     */
    @PostMapping
    public ResponseEntity<LocationResource> updateLocation(@RequestBody CreateLocationResource resource) {
        return commandService.handle(CreateLocationCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(location -> new ResponseEntity<>(
                        LocationResourceFromEntityAssembler.toResourceFromEntity(location),
                        HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Devuelve el historial completo de ubicaciones registradas.
     */
    @GetMapping
    public List<LocationResource> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve la última ubicación registrada de un vehículo.
     */
    @GetMapping("/current")
    public ResponseEntity<LocationResource> getCurrentLocation(@RequestParam Long vehicleId) {
        return commandService.getCurrentLocation(vehicleId)
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Devuelve la lista de ubicaciones (ruta) asociada a un viaje específico.
     */
    @GetMapping("/trip/{tripId}")
    public List<LocationResource> getLocationsByTripId(@PathVariable Long tripId) {
        return locationRepository.findByTrip_IdOrderByTimestampAsc(tripId).stream()
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}