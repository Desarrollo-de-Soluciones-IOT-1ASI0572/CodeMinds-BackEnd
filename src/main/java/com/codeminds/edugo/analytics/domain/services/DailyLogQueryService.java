package com.codeminds.edugo.analytics.domain.services;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import com.codeminds.edugo.analytics.domain.model.dto.DriverDashboard;
import com.codeminds.edugo.analytics.domain.model.querys.GetAllDailyLogQuery;
import com.codeminds.edugo.analytics.domain.model.querys.GetAllDailyLogsByDriverIdQuery;
import com.codeminds.edugo.analytics.domain.model.querys.GetDailyLogByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DailyLogQueryService {
    List<DailyLog> handle(GetAllDailyLogQuery query);
    Optional<DriverDashboard> handle(GetDailyLogByIdQuery query);
    List<DailyLog> handle(GetAllDailyLogsByDriverIdQuery query);
}
