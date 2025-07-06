package com.codeminds.edugo.tracking.interfaces.rest;

import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.codeminds.edugo.tracking.application.internal.commandservices.TrackingCommandServiceImpl;
import com.codeminds.edugo.tracking.domain.model.entities.Location;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.LocationRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.VehicleRepository;
import com.codeminds.edugo.tracking.interfaces.rest.resources.*;
import com.codeminds.edugo.tracking.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicle Management", description = "Endpoints for vehicle management")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);
    private final TrackingCommandServiceImpl commandService;
    private final VehicleRepository vehicleRepository;
    private final TripStudentRepository tripStudentRepository;
    private final LocationRepository locationRepository;

    public VehicleController(TrackingCommandServiceImpl commandService,
                             StudentRepository studentRepository,
                             VehicleRepository vehicleRepository,
                             TripStudentRepository tripStudentRepository,
                             LocationRepository locationRepository,
                             ProfileRepository profileRepository) {
        this.commandService = commandService;
        this.vehicleRepository = vehicleRepository;
        this.tripStudentRepository = tripStudentRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * Crea un nuevo vehículo en el sistema.
     */
    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource) {
        return commandService.handle(CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(vehicle -> new ResponseEntity<>(
                        VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                        HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Devuelve todos los vehículos registrados.
     */
    @GetMapping
    public List<VehicleResource> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }

    /**
     * Obtiene la ubicación actual del vehículo asociado a un estudiante
     */
    @GetMapping("/students/{studentId}/current-vehicle-location")
    public ResponseEntity<?> getCurrentVehicleLocationByStudent(@PathVariable Long studentId) {
        try {
            if (studentId == null || studentId <= 0) {
                return ResponseEntity.badRequest().body("Student ID must be positive");
            }

            TripStudent activeTrip = tripStudentRepository
                    .findActiveByStudentIdOrdered(studentId, PageRequest.of(0, 1))
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (activeTrip == null || activeTrip.getTrip() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No active trip found for student");
            }

            Optional<Location> lastLocation = locationRepository
                    .findFirstByTripIdOrderByTimestampDesc(activeTrip.getTrip().getId());

            if (lastLocation.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No location data available for this trip");
            }

            VehicleLocationResource resource = new VehicleLocationResource(
                    activeTrip.getTrip().getVehicle().getId(),
                    activeTrip.getTrip().getId(),
                    new GeoPoint(
                            lastLocation.get().getLatitude(),
                            lastLocation.get().getLongitude()
                    ),
                    lastLocation.get().getTimestamp()
            );

            return ResponseEntity.ok(resource);

        } catch (Exception e) {
            log.error("Error fetching vehicle location for student {}: {}", studentId, e.getMessage());
            return ResponseEntity.internalServerError()
                    .body("Could not retrieve location data");
        }
    }
}