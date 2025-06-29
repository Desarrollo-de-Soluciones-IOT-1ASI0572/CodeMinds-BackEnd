package com.codeminds.edugo.vehicule.interfaces.rest;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.codeminds.edugo.vehicule.application.internal.commandservices.TrackingCommandServiceImpl;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.DeleteTripCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.commands.DeleteTripCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.LocationRepository;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.TripRepository;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.VehicleRepository;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.*;
import com.codeminds.edugo.vehicule.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/vehicle-tracking")
@Tag(name = "Vehicle Tracking", description = "Endpoints for managing vehicle tracking")
public class VehicleTrackingController{

    private static final Logger log = LoggerFactory.getLogger(VehicleTrackingController.class);
    private final TrackingCommandServiceImpl commandService;

    private final TripRepository tripRepository;

    private final VehicleRepository vehicleRepository;

    private final TripStudentRepository tripStudentRepository;

    private final LocationRepository locationRepository;

    private final ProfileRepository profileRepository;


    public VehicleTrackingController(TrackingCommandServiceImpl commandService, TripRepository tripRepository, VehicleRepository vehicleRepository, TripStudentRepository tripStudentRepository, LocationRepository locationRepository, ProfileRepository profileRepository) {
        this.commandService = commandService;
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
        this.tripStudentRepository = tripStudentRepository;
        this.locationRepository = locationRepository;
        this.profileRepository = profileRepository;
    }

    /**
     * Crea un nuevo vehículo en el sistema.
     */
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource) {
        return commandService.handle(CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(vehicle -> new ResponseEntity<>(
                        VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                        HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }


    /**
     * Registra un nuevo viaje a partir de un vehículo y su ruta.
     */
    @PostMapping("/trips")
    public ResponseEntity<TripResource> createTrip(@RequestBody CreateTripResource resource) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(resource.vehicleId());
        if (optionalVehicle.isEmpty()) return ResponseEntity.badRequest().build();

        Optional<Profile> optionalDriver = profileRepository.findById(resource.driverId());
        if (optionalDriver.isEmpty()) return ResponseEntity.badRequest().build();

        Trip trip = new Trip(
                optionalVehicle.get(),
                optionalDriver.get(),
                resource.origin(),
                resource.destination()
        );
        Trip savedTrip = tripRepository.save(trip);

        return new ResponseEntity<>(TripResourceFromEntityAssembler.toResourceFromEntity(savedTrip), HttpStatus.CREATED);
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
     */
    @PostMapping("/routes/start")
    public ResponseEntity<VehicleResource> startRoute(@RequestBody StartRouteResource resource) {
        return commandService.handle(StartRouteCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(vehicle -> new ResponseEntity<>(
                        VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                        CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Actualiza la ubicación de un vehículo en tiempo real.
     */
    @PostMapping("/locations")
    public ResponseEntity<LocationResource> updateLocation(@RequestBody CreateLocationResource resource) {
        return commandService.handle(CreateLocationCommandFromResourceAssembler.toCommandFromResource(resource))
                .map(location -> new ResponseEntity<>(
                        LocationResourceFromEntityAssembler.toResourceFromEntity(location),
                        CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Finaliza la ruta activa de un vehículo.
     */
    @PostMapping("/routes/end")
    public ResponseEntity<Void> endRoute(@RequestBody EndRouteResource resource) {
        commandService.handle(EndRouteCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntity.ok().build();
    }

    /**
     * Registra el abordaje de un estudiante al transporte.
     */
    @PostMapping("/boarding")
    public ResponseEntity<Void> registerBoarding(@RequestBody RegisterStudentBoardingResource resource) {
        commandService.handle(RegisterStudentBoardingCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntity.ok().build();
    }

    /**
     * Registra la bajada de un estudiante del transporte.
     */
    @PostMapping("/exit")
    public ResponseEntity<Void> registerExit(@RequestBody RegisterStudentExitResource resource) {
        commandService.handle(RegisterStudentExitCommandFromResourceAssembler.toCommandFromResource(resource));
        return ResponseEntity.ok().build();
    }


    /**
     * Devuelve todos los vehículos registrados.
     */
    @GetMapping("/vehicles")
    public List<VehicleResource> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    /**
     * Devuelve todos los viajes registrados.
     */
    @GetMapping("/trips")
    public List<TripResource> getAllTrips() {
        return tripRepository.findAll().stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    /**
     * Devuelve los detalles de un viaje por su ID.
     */
    @GetMapping("/trips/{id}")
    public ResponseEntity<TripResource> getTripById(@PathVariable Long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        return optionalTrip.map(trip ->
                ResponseEntity.ok(TripResourceFromEntityAssembler.toResourceFromEntity(trip))
        ).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Devuelve todos los estudiantes registrados en un viaje específico.
     */
    @GetMapping("/trips/{tripId}/students")
    public List<TripStudentResource> getTripStudentsByTripId(@PathVariable Long tripId) {
        return tripStudentRepository.findByTrip_Id(tripId).stream()
                .map(TripStudentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    /**
     * Devuelve un estudiante específico dentro de un viaje.
     */
    @GetMapping("/trips/{tripId}/students/{studentId}")
    public ResponseEntity<TripStudentResource> getTripStudentByTripIdAndStudentId(
            @PathVariable Long tripId,
            @PathVariable Long studentId
    ) {
        TripStudent ts = tripStudentRepository.findByTrip_IdAndStudentId(tripId, studentId);
        if (ts == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TripStudentResourceFromEntityAssembler.toResourceFromEntity(ts));
    }

    /**
     * Devuelve el historial completo de ubicaciones registradas.
     */
    @GetMapping("/locations")
    public List<LocationResource> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    /**
     * Devuelve la última ubicación registrada de un vehículo.
     */
    @GetMapping("/locations/current")
    public ResponseEntity<LocationResource> getCurrentLocation(@RequestParam Long vehicleId) {
        return commandService.getCurrentLocation(vehicleId)
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Devuelve la lista de ubicaciones (ruta) asociada a un viaje específico.
     */
    @GetMapping("/locations/trip/{tripId}")
    public List<LocationResource> getLocationsByTripId(@PathVariable Long tripId) {
        return locationRepository.findByTrip_IdOrderByTimestampAsc(tripId).stream()
                .map(LocationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    // Endpoint para eliminar un viaje
    /*@DeleteMapping("/trips/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable int id) {
        boolean isDeleted = tripService.deleteTripById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Trip deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trip not found.");
        }
    }*/

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {
        DeleteTripCommand command = new DeleteTripCommand(id); // Crear el comando con el ID del viaje
        boolean success = commandService.handle(command); // Llamamos al servicio para manejar la eliminación

        if (success) {
            return ResponseEntity.ok("Trip deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Trip not found.");
        }
    }

    /**
     * Obtiene la ubicación actual del vehículo asociado a un estudiante
     */
    @GetMapping("/students/{studentId}/current-vehicle-location")
    public ResponseEntity<?> getCurrentVehicleLocationByStudent(
            @PathVariable Long studentId) {

        try {
            // 1. Validar studentId
            if (studentId == null || studentId <= 0) {
                return ResponseEntity.badRequest().body("Student ID must be positive");
            }

            // 2. Buscar viaje activo
            TripStudent activeTrip = tripStudentRepository
                    .findActiveByStudentIdOrdered(studentId, PageRequest.of(0, 1))
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (activeTrip == null || activeTrip.getTrip() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No active trip found for student");
            }

            // 3. Obtener última ubicación
            Optional<Location> lastLocation = locationRepository
                    .findFirstByTripIdOrderByTimestampDesc(activeTrip.getTrip().getId());

            if (lastLocation.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No location data available for this trip");
            }

            // 4. Construir respuesta
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

    @GetMapping("/trips/completed")
    public List<TripResource> getCompletedTrips() {
        return tripRepository.findByEndTimeIsNotNull().stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    @GetMapping("/trips/completed/driver/{driverId}")
    public List<TripResource> getCompletedTripsByDriver(@PathVariable Long driverId) {
        return tripRepository.findByEndTimeIsNotNullAndVehicle_DriverId(driverId).stream()
                .map(TripResourceFromEntityAssembler::toResourceFromEntity)
                .collect(toList());
    }

    @GetMapping("/trips/active/driver/{driverId}")
    public ResponseEntity<List<ActiveTripResource>> getActiveTripByDriver(@PathVariable Long driverId) {
        List<Trip> activeTrips = tripRepository.findActiveTripByDriverId(driverId)
                .map(Collections::singletonList) // Convierte el Optional<Trip> a List<Trip>
                .orElse(Collections.emptyList()); // Si no hay viaje, devuelve lista vacía

        List<ActiveTripResource> response = activeTrips.stream()
                .map(ActiveTripResourceAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }




}
