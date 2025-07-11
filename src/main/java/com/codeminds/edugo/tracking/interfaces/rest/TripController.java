package com.codeminds.edugo.tracking.interfaces.rest;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.codeminds.edugo.tracking.application.internal.commandservices.TrackingCommandServiceImpl;
import com.codeminds.edugo.tracking.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.tracking.domain.model.commands.ActivateEmergencyCommand;
import com.codeminds.edugo.tracking.domain.model.commands.DeleteTripCommand;
import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.domain.model.valueobjects.TripStatus;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.VehicleRepository;
import com.codeminds.edugo.tracking.interfaces.rest.resources.*;
import com.codeminds.edugo.tracking.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/trips")
@Tag(name = "Trip Management", description = "Endpoints for managing trips and trip-related operations")
public class TripController {

    private final TrackingCommandServiceImpl commandService;
    private final TripRepository tripRepository;
    private final StudentRepository studentRepository;
    private final VehicleRepository vehicleRepository;
    private final TripStudentRepository tripStudentRepository;
    private final ProfileRepository profileRepository;

    public TripController(TrackingCommandServiceImpl commandService,
            TripRepository tripRepository,
            StudentRepository studentRepository,
            VehicleRepository vehicleRepository,
            TripStudentRepository tripStudentRepository,
            ProfileRepository profileRepository) {
        this.commandService = commandService;
        this.tripRepository = tripRepository;
        this.studentRepository = studentRepository;
        this.vehicleRepository = vehicleRepository;
        this.tripStudentRepository = tripStudentRepository;
        this.profileRepository = profileRepository;
    }

    /**
     * Registra un nuevo viaje a partir de un vehículo y su ruta.
     */
    @PostMapping
    public ResponseEntity<TripResource> createTrip(@RequestBody CreateTripResource resource) {
        // 1. Validar vehicle y driver (existente)
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(resource.vehicleId());
        Optional<Profile> optionalDriver = profileRepository.findById(resource.driverId());
        if (optionalVehicle.isEmpty() || optionalDriver.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // 2. Crear y guardar el Trip
        Trip trip = new Trip(
                optionalVehicle.get(),
                optionalDriver.get(),
                resource.origin(),
                resource.destination());
        Trip savedTrip = tripRepository.save(trip);

        // 3. Obtener estudiantes del driver y crear TripStudent
        List<Student> students = studentRepository.findByDriverId(resource.driverId());
        for (Student student : students) {
            TripStudent tripStudent = new TripStudent(student);
            savedTrip.addStudent(tripStudent);
        }
        tripRepository.save(savedTrip);

        return ResponseEntity.ok(TripResourceFromEntityAssembler.toResourceFromEntity(savedTrip));
    }

    /**
     * Devuelve todos los viajes registrados.
     */
    @GetMapping
    public List<TripResource> getAllTrips() {
        return tripRepository.findAll().stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve los detalles de un viaje por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TripResource> getTripById(@PathVariable Long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        return optionalTrip.map(trip -> ResponseEntity.ok(TripResourceFromEntityAssembler.toResourceFromEntity(trip)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Devuelve todos los estudiantes registrados en un viaje específico.
     */
    @GetMapping("/{tripId}/students")
    public List<TripStudentResource> getTripStudentsByTripId(@PathVariable Long tripId) {
        return tripStudentRepository.findByTrip_Id(tripId).stream()
                .map(TripStudentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve un estudiante específico dentro de un viaje.
     */
    @GetMapping("/{tripId}/students/{studentId}")
    public ResponseEntity<TripStudentResource> getTripStudentByTripIdAndStudentId(
            @PathVariable Long tripId,
            @PathVariable Long studentId) {
        TripStudent ts = tripStudentRepository.findByTrip_IdAndStudentId(tripId, studentId);
        if (ts == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TripStudentResourceFromEntityAssembler.toResourceFromEntity(ts));
    }

    /**
     * Elimina un viaje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {
        DeleteTripCommand command = new DeleteTripCommand(id);
        boolean success = commandService.handle(command);

        if (success) {
            return ResponseEntity.ok("Trip deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Trip not found.");
        }
    }

    /**
     * Devuelve viajes completados
     */
    @GetMapping("/completed")
    public List<TripResource> getCompletedTrips() {
        return tripRepository.findByEndTimeIsNotNull().stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve viajes completados por conductor
     */
    @GetMapping("/completed/driver/{driverId}")
    public List<TripResource> getCompletedTripsByDriver(@PathVariable Long driverId) {
        return tripRepository.findByEndTimeIsNotNullAndVehicle_DriverId(driverId).stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve viaje activo por conductor
     */
    @GetMapping("/active/driver/{driverId}")
    public ResponseEntity<List<ActiveTripResource>> getActiveTripByDriver(@PathVariable Long driverId) {
        // Cambiar para incluir tanto 'CREATED' como 'IN_PROGRESS'
        List<Trip> activeTrips = tripRepository.findByDriver_IdAndStatusIn(driverId,
                List.of(TripStatus.CREATED, TripStatus.IN_PROGRESS));

        List<ActiveTripResource> response = activeTrips.stream()
                .map(ActiveTripResourceAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * Asocia un estudiante a un viaje determinado.
     */
    @PostMapping("/trip-students")
    public ResponseEntity<TripStudentResource> createTripStudent(@RequestBody CreateTripStudentResource resource) {
        return commandService.handle(CreateTripStudentCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(student -> new ResponseEntity<>(
                        TripStudentResourceFromEntityAssembler.toResourceFromEntity(student),
                        HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Marca el inicio de una ruta para un vehículo.
     * 
     * @PutMapping("/{tripId}/start")
     * public ResponseEntity<VehicleResource> startRoute(@PathVariable Long tripId)
     * {
     * return
     * commandService.handle(StartRouteCommandFromResourceAssembler.toCommandFromResource(tripId))
     * .map(vehicle -> new ResponseEntity<>(
     * VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
     * CREATED))
     * .orElse(ResponseEntity.badRequest().build());
     * }
     * 
     * /**
     * Finaliza la ruta activa de un vehículo.
     * 
     * @PutMapping("/{tripId}/end")
     * public ResponseEntity<Void> endRoute(@PathVariable Long tripId) {
     * commandService.handle(EndRouteCommandFromResourceAssembler.toCommandFromResource(tripId));
     * return ResponseEntity.ok().build();
     * }
     * /*
     */
    @PutMapping("/{tripId}/status")
    public ResponseEntity<TripResource> updateTripStatus(@PathVariable Long tripId,
            @RequestBody UpdateTripStatusResource resource) {

        if (resource.status() == null) {
            return ResponseEntity.badRequest().build();
        }

        return commandService
                .handle(UpdateTripStatusCommandFromResourceAssembler.toCommandFromResource(tripId, resource))
                .map(trip -> ResponseEntity.ok(TripResourceFromEntityAssembler.toResourceFromEntity(trip)))
                .orElse(ResponseEntity.notFound().build()); // Más específico que badRequest
    }

    @PutMapping("/{tripId}/emergency")
    public ResponseEntity<Void> activateEmergency(@PathVariable Long tripId) {
        commandService.handle(new ActivateEmergencyCommand(tripId));
        return ResponseEntity.ok().build();
    }

}