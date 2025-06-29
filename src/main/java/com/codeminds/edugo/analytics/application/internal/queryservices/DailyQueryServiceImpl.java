package com.codeminds.edugo.analytics.application.internal.queryservices;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import com.codeminds.edugo.analytics.domain.model.dto.DriverDashboard;
import com.codeminds.edugo.analytics.domain.model.dto.IncidentSummary;
import com.codeminds.edugo.analytics.domain.model.querys.GetAllDailyLogQuery;
import com.codeminds.edugo.analytics.domain.model.querys.GetDailyLogByIdQuery;
import com.codeminds.edugo.analytics.domain.services.DailyLogQueryService;
import com.codeminds.edugo.analytics.infrastructure.persistence.jpa.DailyLogRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DailyQueryServiceImpl implements DailyLogQueryService {

    private final DailyLogRepository dailyLogRepository;

    public DailyQueryServiceImpl(DailyLogRepository dailyLogRepository) {
        this.dailyLogRepository = dailyLogRepository;
    }

    @Override
    public List<DailyLog> handle(GetAllDailyLogQuery query){
        return dailyLogRepository.findAll();
    }

    @Override
    public Optional<DriverDashboard> handle(GetDailyLogByIdQuery query) {
        Long driverId = query.driverId();
        List<DailyLog> logs = dailyLogRepository.findByDriverUserId(driverId);

        Map<DayOfWeek, List<Double>> speedByDay = new HashMap<>();
        for (DailyLog log : logs) {
            DayOfWeek day = log.getDate().getDayOfWeek();
            if (day.getValue() <= 5) {
                speedByDay.computeIfAbsent(day, k -> new ArrayList<>()).add(log.getSpeed());
            }
        }

        List<DriverDashboard.SpeedPerDay> speedPerWeek = speedByDay.entrySet().stream()
                .map(entry -> new DriverDashboard.SpeedPerDay(
                        entry.getKey().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0)))
                .sorted(Comparator.comparing(entry -> DayOfWeek.valueOf(entry.day().toUpperCase())))
                .collect(Collectors.toList());

        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        List<DailyLog> thisWeekLogs = logs.stream()
                .filter(log -> !log.getDate().isBefore(monday) && !log.getDate().isAfter(today))
                .collect(Collectors.toList());

        List<DriverDashboard.ArrivalTime> arrivalTimes = thisWeekLogs.stream()
                .map(log -> new DriverDashboard.ArrivalTime(
                        log.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        log.getArrivalTimeAtSchool().toString().substring(0, 5)))
                .sorted(Comparator.comparing(entry -> DayOfWeek.valueOf(entry.day().toUpperCase())))
                .collect(Collectors.toList());

        int detour = 0, lateness = 0, speeding = 0;
        for (DailyLog log : logs) {
            if (log.getIncident() != null) {
                if (log.getIncident().isDetour()) detour++;
                if (log.getIncident().isLateness()) lateness++;
                if (log.getIncident().isSpeeding()) speeding++;
            }
        }

        IncidentSummary summary = new IncidentSummary(detour, lateness, speeding);
        String week = monday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " - " + monday.plusDays(4).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        return Optional.of(new DriverDashboard(driverId, week, speedPerWeek, arrivalTimes, summary));
    }

}
