package com.codeminds.edugo.tracking.application.internal.commandservices;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
import com.codeminds.edugo.shared.domain.model.bus.DomainEventPublisher;
import com.codeminds.edugo.tracking.domain.events.*;
import com.codeminds.edugo.tracking.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.tracking.domain.model.commands.*;
import com.codeminds.edugo.tracking.domain.model.entities.Location;
import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.domain.model.valueobjects.TripStatus;
import com.codeminds.edugo.tracking.domain.services.TrackingCommandService;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.LocationRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TrackingCommandServiceImpl implements TrackingCommandService {

    private final LocationRepository locationRepository;
    private final VehicleRepository vehicleRepository;

    private final TripRepository tripRepository;

    private final TripStudentRepository tripStudentRepository;
    private final DomainEventPublisher eventPublisher;

    private final StudentRepository studentRepository;

    private static final double SPEED_LIMIT = 60.0;

    public TrackingCommandServiceImpl(LocationRepository locationRepository, VehicleRepository vehicleRepository,
            TripRepository tripRepository, TripStudentRepository tripStudentRepository,
            DomainEventPublisher eventPublisher, StudentRepository studentRepository) {
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository;
        this.tripRepository = tripRepository;
        this.tripStudentRepository = tripStudentRepository;
        this.eventPublisher = eventPublisher;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Vehicle> handle(StartRouteCommand command) {
        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());
        if (optionalTrip.isEmpty())
            return Optional.empty();

        Trip trip = optionalTrip.get();
        Vehicle vehicle = trip.getVehicle();

        trip.startTrip();
        tripRepository.save(trip);

        vehicle.startMoving();
        vehicleRepository.save(vehicle);

        eventPublisher.publish(new TripStartedEvent(
                trip.getId(),
                vehicle.getId(),
                vehicle.getDriverId().longValue(),
                trip.getOrigin(),
                trip.getDestination(),
                trip.getStartTime()));

        return Optional.of(vehicle);
    }

    @Override
    public void handle(EndRouteCommand command) {
        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());
        if (optionalTrip.isEmpty())
            return;

        Trip trip = optionalTrip.get();
        trip.endTrip();
        tripRepository.save(trip);

        Vehicle vehicle = trip.getVehicle();
        vehicle.stopMoving();
        vehicleRepository.save(vehicle);

        eventPublisher.publish(new TripEndedEvent(
                trip.getId(),
                vehicle.getId(),
                trip.getDriver().getId(),
                trip.getOrigin(),
                trip.getDestination(),
                trip.getStartTime()));
    }

    @Override
    public Optional<Location> handle(CreateLocationCommand command) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(command.vehicleId());
        if (optionalVehicle.isEmpty())
            return Optional.empty();

        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());
        if (optionalTrip.isEmpty())
            return Optional.empty();

        Location location = new Location(
                command.vehicleId(),
                command.latitude(),
                command.longitude(),
                command.speed(),
                optionalTrip.get());

        Location saved = locationRepository.save(location);

        if (location.isSpeedLimitExceeded(SPEED_LIMIT)) {
            eventPublisher.publish(new SpeedExceededEvent(
                    command.vehicleId(),
                    command.speed(),
                    command.latitude(),
                    command.longitude(),
                    location.getTimestamp()));
        }

        return Optional.of(saved);
    }

    @Override
    public void handle(RegisterStudentBoardingCommand command) {
        TripStudent tripStudent = tripStudentRepository
                .findByTrip_IdAndStudentId(command.tripId(), command.studentId());

        tripStudent.markAttendance(command.boardedAt());
        tripStudentRepository.save(tripStudent);

        eventPublisher.publish(new StudentBoardedEvent(
                command.studentId(),
                command.tripId(),
                command.boardedAt()));
    }

    @Override
    public void handle(RegisterStudentExitCommand command) {
        TripStudent tripStudent = tripStudentRepository
                .findByTrip_IdAndStudentId(command.tripId(), command.studentId());

        tripStudent.markExit(command.exitedAt());
        tripStudentRepository.save(tripStudent);

        eventPublisher.publish(new StudentExitedEvent(
                command.studentId(),
                command.tripId(),
                command.exitedAt()));
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        Vehicle vehicle = new Vehicle(command.driverId(), command.capacity());
        Vehicle saved = vehicleRepository.save(vehicle);
        return Optional.of(saved);
    }

    @Override
    public Optional<TripStudent> handle(CreateTripStudentCommand command) {
        TripStudent existing = tripStudentRepository.findByTrip_IdAndStudentId(command.tripId(), command.studentId());
        if (existing != null)
            return Optional.of(existing);

        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());
        Optional<Student> optionalStudent = studentRepository.findById(command.studentId());

        if (optionalTrip.isEmpty() || optionalStudent.isEmpty())
            return Optional.empty();

        Trip trip = optionalTrip.get();
        Student student = optionalStudent.get();

        TripStudent tripStudent = new TripStudent(student);
        trip.addStudent(tripStudent);

        TripStudent saved = tripStudentRepository.save(tripStudent);
        return Optional.of(saved);
    }

    public Optional<Location> getCurrentLocation(Long vehicleId) {
        return locationRepository.findLastLocation(vehicleId);
    }

    @Transactional
    @Override
    public boolean handle(DeleteTripCommand command) {
        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());

        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();

            trip.getLocations().size();
            trip.getStudents().size();

            tripRepository.delete(trip);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Trip> handle(UpdateTripStatusCommand command) {
        Optional<Trip> optionalTrip = tripRepository.findById(command.tripId());
        if (optionalTrip.isEmpty()) {
            return Optional.empty();
        }

        Trip trip = optionalTrip.get();
        TripStatus oldStatus = trip.getStatus();

        trip.updateStatus(command.status());
        Trip savedTrip = tripRepository.save(trip);

        if (oldStatus != TripStatus.IN_PROGRESS && command.status() == TripStatus.IN_PROGRESS) {
            System.out.println("📢 PUBLICANDO TripStartedEvent desde UpdateTripStatus");
            eventPublisher.publish(new TripStartedEvent(
                    trip.getId(),
                    trip.getVehicle().getId(),
                    trip.getDriver().getId(),
                    trip.getOrigin(),
                    trip.getDestination(),
                    trip.getStartTime()
            ));
        } else if (oldStatus != TripStatus.COMPLETED && command.status() == TripStatus.COMPLETED) {
            System.out.println("📢 PUBLICANDO TripEndedEvent desde UpdateTripStatus");
            eventPublisher.publish(new TripEndedEvent(
                    trip.getId(),
                    trip.getVehicle().getId(),
                    trip.getDriver().getId(),
                    trip.getOrigin(),
                    trip.getDestination(),
                    trip.getStartTime()
            ));
        }

        return Optional.of(savedTrip);
    }

    @Transactional
    public void handle(ActivateEmergencyCommand command) {
        System.out.println("🚨 [EMERGENCY] - Iniciando activación de emergencia para tripId: " + command.tripId());

        Trip trip = tripRepository.findById(command.tripId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        trip.markAsEmergency();

        trip.endTrip();

        tripRepository.save(trip);
        System.out.println("🚨 [EMERGENCY] - Trip marcado como EMERGENCY y finalizado, guardado en base de datos.");

        Optional<Location> lastLocation = getCurrentLocation(trip.getVehicle().getId());

        if (lastLocation.isPresent()) {
            System.out.println("📍 [EMERGENCY] - Última ubicación encontrada:");
            System.out.println("  Latitude: " + lastLocation.get().getLatitude());
            System.out.println("  Longitude: " + lastLocation.get().getLongitude());
        } else {
            System.out.println("⚠️ [EMERGENCY] - No se encontró ubicación previa para este vehículo.");
        }

        Double latitude = lastLocation.map(Location::getLatitude).orElse(null);
        Double longitude = lastLocation.map(Location::getLongitude).orElse(null);

        if (latitude != null && longitude != null) {
            Location emergencyLocation = new Location(
                    trip.getVehicle().getId(),
                    latitude,
                    longitude,
                    0.0, // speed
                    trip
            );
            locationRepository.save(emergencyLocation);
            System.out.println("✅ [EMERGENCY] - Ubicación de emergencia guardada con lat: " + latitude + ", lon: " + longitude);
        }

        eventPublisher.publish(new EmergencyEvent(
                trip.getId(),
                trip.getDriver().getId(),
                latitude,
                longitude
        ));
        System.out.println("📢 [EMERGENCY] - EmergencyEvent publicado con coordenadas. 🚨");
    }



}
