package com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.wristband;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.wristband.WristbandResource;

public class WristbandFromEntityAssembler {
    public static WristbandResource toResourceFromEntity(Wristband entity) {
        return new WristbandResource(
                entity.getId(),
                entity.getRfidCode(),
                entity.getWristbandStatus(),
                entity.getStudent(),
                entity.getSensorScanList()
        );
    }
} 