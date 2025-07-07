package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignments.interfaces.rest.resources.SensorScanResource;

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
