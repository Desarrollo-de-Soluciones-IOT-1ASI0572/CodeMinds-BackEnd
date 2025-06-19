package com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.sensorscan;

import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan.SensorScanResource;

public class SensorScanResourceFromEntityAssembler {
    public static SensorScanResource toResourceFromEntity(SensorScan entity) {
        return new SensorScanResource(
                entity.getId(),
                entity.getScanTime(),
                entity.getScanType(),
                entity.getWristband()
        );
    }
}
