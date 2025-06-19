package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan.SensorScanCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.SensorScanRepository;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        return wristbandRepository.findById(command.wristbandId())
                .map(wristband -> {
                    var sensorScan = new SensorScan(
                            command.scanType(),
                            wristband,
                            command.scanTime() != null ? command.scanTime() : LocalDateTime.now()
                    );
                    wristband.getSensorScanList().add(sensorScan);
                    return sensorScanRepository.save(sensorScan);
                });
    }

    @Override
    public void handle(DeleteSensorScanCommand command) {
        sensorScanRepository.deleteById(command.sensorScanId());
    }
}
