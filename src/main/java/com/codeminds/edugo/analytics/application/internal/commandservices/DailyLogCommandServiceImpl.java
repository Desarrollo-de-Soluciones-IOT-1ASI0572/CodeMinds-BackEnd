package com.codeminds.edugo.analytics.application.internal.commandservices;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import com.codeminds.edugo.analytics.domain.model.aggregates.Incident;
import com.codeminds.edugo.analytics.domain.model.commands.CreateDailyLogCommand;
import com.codeminds.edugo.analytics.domain.services.DailyLogCommandService;
import com.codeminds.edugo.analytics.infrastructure.persistence.jpa.DailyLogRepository;
import com.codeminds.edugo.analytics.infrastructure.persistence.jpa.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyLogCommandServiceImpl implements DailyLogCommandService {

    private final DailyLogRepository dailyLogRepository;
    private final IncidentRepository incidentRepository;

    public DailyLogCommandServiceImpl(DailyLogRepository dailyLogRepository, IncidentRepository incidentRepository) {
        this.dailyLogRepository = dailyLogRepository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Optional<DailyLog> handle(CreateDailyLogCommand command) {
        try {
            var log = new DailyLog();
            log.setDriverUserId(command.driverUserId());
            log.setDate(command.date());
            log.setArrivalTimeAtSchool(command.arrivalTimeAtSchool());
            log.setReturnTimeHome(command.returnTimeHome());
            log.setSpeed(command.speed());

            dailyLogRepository.save(log);

            var incident = new Incident();
            incident.setDetour(command.detour());
            incident.setLateness(command.lateness());
            incident.setSpeeding(command.speeding());
            incident.setDailyLog(log);

            incidentRepository.save(incident);

            return Optional.of(log);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
