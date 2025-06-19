package com.codeminds.edugo.analytics.domain.services;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import com.codeminds.edugo.analytics.domain.model.commands.CreateDailyLogCommand;

import java.util.Optional;

public interface DailyLogCommandService {
    Optional<DailyLog> handle(CreateDailyLogCommand command);

}
