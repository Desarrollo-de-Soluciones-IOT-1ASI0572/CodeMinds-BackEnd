/*package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;
import com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan.SensorScanCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.SensorScanRepository;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SensorScarCommandServiceImpl implements SensorScanCommandService {
    private final SensorScanRepository sensorScanRepository;

    private final WristbandRepository wristbandRepository;

    private final TripStudentRepository tripStudentRepository;


    public SensorScarCommandServiceImpl(SensorScanRepository sensorScanRepository, WristbandRepository wristbandRepository, TripStudentRepository tripStudentRepository) {
        this.sensorScanRepository = sensorScanRepository;
        this.wristbandRepository = wristbandRepository;
        this.tripStudentRepository = tripStudentRepository;
    }

    /*@Override
    public Optional<SensorScan> handle(CreateSensorScanCommand command) {
        var wristband = wristbandRepository.findById(command.wristbandId())
                .orElseThrow(() -> new IllegalArgumentException("Wristband not found"));

        var sensorScan = new SensorScan(
                command.scanType(),
                wristband,
                command.scanTime()
        );

        var createdSensorScan = sensorScanRepository.save(sensorScan);
        return Optional.of(createdSensorScan);
    }

    @Override
    public Optional<SensorScan> handle(CreateSensorScanCommand command) {
        // 1. Validar wristband
        var wristband = wristbandRepository.findById(command.wristbandId())
                .orElseThrow(() -> new IllegalArgumentException("Pulsera no registrada"));

        // 2. Buscar TripStudent
        var tripStudent = tripStudentRepository.findByTrip_IdAndStudentId(
                command.tripId(),
                wristband.getStudent().getId()
        );

        if (command.scanType() == ScanType.ENTRY && tripStudent.getBoardedAt() != null) {
            throw new IllegalStateException("Estudiante ya ingresó al vehículo (ENTRY duplicado)");
        }
        else if (command.scanType() == ScanType.EXIT) {
            if (tripStudent.getBoardedAt() == null) {
                throw new IllegalStateException("Estudiante no ha ingresado (EXIT sin ENTRY previo)");
            }
            if (tripStudent.getExitedAt() != null) {
                throw new IllegalStateException("Estudiante ya salió del vehículo (EXIT duplicado)");
            }
        }

        if (command.scanType() == ScanType.ENTRY) {
            tripStudent.markAttendance(command.scanTime());
        } else {
            tripStudent.markExit(command.scanTime());
        }
        tripStudentRepository.save(tripStudent);

        var sensorScan = new SensorScan(
                command.scanType(),
                wristband,
                command.scanTime()
        );
        return Optional.of(sensorScanRepository.save(sensorScan));
    }

    @Override
    public void handle(DeleteSensorScanCommand command) {
        sensorScanRepository.deleteById(command.sensorScanId());
    }
}*/

package com.codeminds.edugo.assignments.application.internal.commandservices;

import com.codeminds.edugo.assignments.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignments.domain.models.valueobjects.ScanType;
import com.codeminds.edugo.assignments.domain.services.SensorScanCommandService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.SensorScanRepository;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.WristbandRepository;
import com.codeminds.edugo.tracking.domain.events.StudentBoardedEvent;
import com.codeminds.edugo.tracking.domain.events.StudentExitedEvent;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class SensorScarCommandServiceImpl implements SensorScanCommandService {
    private final SensorScanRepository sensorScanRepository;
    private final WristbandRepository wristbandRepository;
    private final TripStudentRepository tripStudentRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SensorScarCommandServiceImpl(
            SensorScanRepository sensorScanRepository,
            WristbandRepository wristbandRepository,
            TripStudentRepository tripStudentRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.sensorScanRepository = sensorScanRepository;
        this.wristbandRepository = wristbandRepository;
        this.tripStudentRepository = tripStudentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<SensorScan> handle(CreateSensorScanCommand command) {
        // 1. Validar wristband
        var wristband = wristbandRepository.findById(command.wristbandId())
                .orElseThrow(() -> new IllegalArgumentException("Pulsera no registrada"));

        Long studentId = wristband.getStudent().getId();
        Long tripId = command.tripId();

        // 2. Buscar TripStudent
        var tripStudent = tripStudentRepository.findByTrip_IdAndStudentId(tripId, studentId);

        // 3. Validar scans duplicados/incongruentes
        validateScan(command.scanType(), tripStudent);

        // 4. Actualizar TripStudent
        updateTripStudent(command.scanType(), tripStudent, command.scanTime());

        // 5. Publicar evento según el tipo de scan
        publishEvent(command.scanType(), studentId, tripId, command.scanTime());

        // 6. Registrar scan
        var sensorScan = new SensorScan(
                command.scanType(),
                wristband,
                command.scanTime()
        );

        return Optional.of(sensorScanRepository.save(sensorScan));
    }

    private void validateScan(ScanType scanType, TripStudent tripStudent) {
        if (scanType == ScanType.ENTRY && tripStudent.getBoardedAt() != null) {
            throw new IllegalStateException("Estudiante ya ingresó al vehículo (ENTRY duplicado)");
        } else if (scanType == ScanType.EXIT) {
            if (tripStudent.getBoardedAt() == null) {
                throw new IllegalStateException("Estudiante no ha ingresado (EXIT sin ENTRY previo)");
            }
            if (tripStudent.getExitedAt() != null) {
                throw new IllegalStateException("Estudiante ya salió del vehículo (EXIT duplicado)");
            }
        }
    }

    private void updateTripStudent(ScanType scanType, TripStudent tripStudent, LocalDateTime scanTime) {
        if (scanType == ScanType.ENTRY) {
            tripStudent.markAttendance(scanTime);
        } else {
            tripStudent.markExit(scanTime);
        }
        tripStudentRepository.save(tripStudent);
    }

    private void publishEvent(ScanType scanType, Long studentId, Long tripId, LocalDateTime timestamp) {
        if (scanType == ScanType.ENTRY) {
            eventPublisher.publishEvent(new StudentBoardedEvent(studentId, tripId, timestamp));
            System.out.println("🚀 Evento StudentBoardedEvent publicado para studentId: " + studentId);
        } else {
            eventPublisher.publishEvent(new StudentExitedEvent(studentId, tripId, timestamp));
            System.out.println("🚀 Evento StudentExitedEvent publicado para studentId: " + studentId);
        }
    }

    @Override
    public void handle(DeleteSensorScanCommand command) {
        sensorScanRepository.deleteById(command.sensorScanId());
    }
}
