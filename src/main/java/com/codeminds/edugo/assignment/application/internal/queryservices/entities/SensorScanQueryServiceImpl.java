package com.codeminds.edugo.assignment.application.internal.queryservices.entities;

import com.codeminds.edugo.assignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetAllSensorScansQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByScanTypeQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByWristbandIdQuery;
import com.codeminds.edugo.assignment.domain.services.entities.sensorscan.SensorScanQueryService;
import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.entities.SensorScanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorScanQueryServiceImpl implements SensorScanQueryService {
    private final SensorScanRepository sensorScanRepository;

    public SensorScanQueryServiceImpl(SensorScanRepository sensorScanRepository) {
        this.sensorScanRepository = sensorScanRepository;
    }

    @Override
    public List<SensorScan> handle(GetAllSensorScansQuery query) {
        return sensorScanRepository.findAll();
    }

    @Override
    public List<SensorScan> handle(GetSensorScansByIdQuery query) {
        return sensorScanRepository.findById(query.sensorScanId())
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<SensorScan> handle(GetSensorScansByWristbandIdQuery query) {
        return sensorScanRepository.findByWristbandId(query.wristbandId())
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public List<SensorScan> handle(GetSensorScansByScanTypeQuery query) {
        return sensorScanRepository.findByScanType(query.scanType());
    }
}
