package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllSensorScansQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetSensorScansByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetSensorScansByScanTypeQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetSensorScansByWristbandIdQuery;

import java.util.List;

public interface SensorScanQueryService {
    List<SensorScan> handle (GetAllSensorScansQuery query);
    List<SensorScan> handle (GetSensorScansByIdQuery query);
    List<SensorScan> handle (GetSensorScansByWristbandIdQuery query);
    List<SensorScan> handle (GetSensorScansByScanTypeQuery query);
}
