package com.codeminds.edugo.analytics.interfaces.transform;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import com.codeminds.edugo.analytics.interfaces.resources.DailyLogResource;
import com.codeminds.edugo.analytics.interfaces.resources.IncidentResource;

public class DailyLogResourceFromEntityAssembler {
    public static DailyLogResource toResourceFromEntity(DailyLog entity) {
        return new DailyLogResource(
                entity.getId(),
                entity.getDriverUserId(),
                entity.getDate().toString(),
                entity.getArrivalTimeAtSchool().toString(),
                entity.getReturnTimeHome().toString(),
                entity.getSpeed(),
                entity.getIncident() == null ? null :
                        new IncidentResource(
                                entity.getIncident().isDetour(),
                                entity.getIncident().isLateness(),
                                entity.getIncident().isSpeeding()
                        )
        );
    }
}
