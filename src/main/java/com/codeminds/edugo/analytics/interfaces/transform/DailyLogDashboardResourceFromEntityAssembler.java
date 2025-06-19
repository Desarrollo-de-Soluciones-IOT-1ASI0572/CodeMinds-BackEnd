package com.codeminds.edugo.analytics.interfaces.transform;

import com.codeminds.edugo.analytics.domain.model.dto.DriverDashboard;
import com.codeminds.edugo.analytics.domain.model.dto.IncidentSummary;
import com.codeminds.edugo.analytics.interfaces.resources.ArrivalTimeResource;
import com.codeminds.edugo.analytics.interfaces.resources.DailyLogDashboardResource;
import com.codeminds.edugo.analytics.interfaces.resources.IncidentSummaryResource;
import com.codeminds.edugo.analytics.interfaces.resources.SpeedPerDayResource;

import java.util.List;
import java.util.stream.Collectors;

public class DailyLogDashboardResourceFromEntityAssembler {
    public static DailyLogDashboardResource toResourceFromEntity(DriverDashboard entity) {
        List<SpeedPerDayResource> speedPerDayResources = entity.getSpeedPerDay().stream()
                .map(s -> new SpeedPerDayResource(s.day(), s.averageSpeed()))
                .collect(Collectors.toList());

        List<ArrivalTimeResource> arrivalTimeResources = entity.getArrivalTimes().stream()
                .map(a -> new ArrivalTimeResource(a.day(), a.time()))
                .collect(Collectors.toList());

        IncidentSummary summary = entity.getIncidentSummary();
        IncidentSummaryResource summaryResource = new IncidentSummaryResource(
                summary.detour(), summary.lateness(), summary.speeding()
        );

        return new DailyLogDashboardResource(
                entity.getDriverUserId(),
                entity.getWeek(),
                speedPerDayResources,
                arrivalTimeResources,
                summaryResource
        );
    }
}
