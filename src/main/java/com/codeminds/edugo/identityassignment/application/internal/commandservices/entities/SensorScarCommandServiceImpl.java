package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan.SensorScanCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.SensorScanRepository;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SensorScarCommandServiceImpl implements SensorScanCommandService {
    private final SensorScanRepository sensorScanRepository;

    private final WristbandRepository wristbandRepository;


    public SensorScarCommandServiceImpl(SensorScanRepository sensorScanRepository, WristbandRepository wristbandRepository) {
        this.sensorScanRepository = sensorScanRepository;
        this.wristbandRepository = wristbandRepository;
    }

    @Override
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
    public void handle(DeleteSensorScanCommand command) {
        sensorScanRepository.deleteById(command.sensorScanId());
    }
}
