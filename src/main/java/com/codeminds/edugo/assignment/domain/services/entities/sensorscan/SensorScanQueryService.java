package com.codeminds.edugo.assignment.domain.services.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetAllSensorScansQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByScanTypeQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.sensorscan.GetSensorScansByWristbandIdQuery;

import java.util.List;

public interface SensorScanQueryService {
    List<SensorScan> handle (GetAllSensorScansQuery query);
    List<SensorScan> handle (GetSensorScansByIdQuery query);
    List<SensorScan> handle (GetSensorScansByWristbandIdQuery query);
    List<SensorScan> handle (GetSensorScansByScanTypeQuery query);
}
