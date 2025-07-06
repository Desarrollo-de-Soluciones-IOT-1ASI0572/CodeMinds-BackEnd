package com.codeminds.edugo.assignments.application.internal.queryservices;

import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignments.domain.models.queries.entities.sensorscan.GetAllSensorScansQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.sensorscan.GetSensorScansByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.sensorscan.GetSensorScansByScanTypeQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.sensorscan.GetSensorScansByWristbandIdQuery;
import com.codeminds.edugo.assignments.domain.services.SensorScanQueryService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.SensorScanRepository;
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
