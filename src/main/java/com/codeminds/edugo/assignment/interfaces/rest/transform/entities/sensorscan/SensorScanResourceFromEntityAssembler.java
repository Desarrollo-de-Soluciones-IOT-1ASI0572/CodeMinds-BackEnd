package com.codeminds.edugo.assignment.interfaces.rest.transform.entities.sensorscan;

import com.codeminds.edugo.assignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.sensorscan.SensorScanResource;

public class SensorScanResourceFromEntityAssembler {
    public static SensorScanResource toResourceFromEntity(SensorScan entity) {
        return new SensorScanResource(
                entity.getId(),
                entity.getScanTime(),
                entity.getScanType(),
                entity.getWristband().getId()
        );
    }
}
