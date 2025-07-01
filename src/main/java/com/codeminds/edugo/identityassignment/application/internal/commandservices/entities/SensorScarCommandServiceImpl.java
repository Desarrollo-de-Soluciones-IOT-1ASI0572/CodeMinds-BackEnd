package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

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
    }*/

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

        // 3. Validar scans duplicados/incongruentes
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

        // 4. Actualizar TripStudent
        if (command.scanType() == ScanType.ENTRY) {
            tripStudent.markAttendance(command.scanTime());
        } else {
            tripStudent.markExit(command.scanTime());
        }
        tripStudentRepository.save(tripStudent);

        // 5. Registrar scan
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
}
