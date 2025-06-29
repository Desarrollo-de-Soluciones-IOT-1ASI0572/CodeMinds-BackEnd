package com.codeminds.edugo.analytics.domain.model.dto;

import java.util.List;

public class DriverDashboard {
    private Long driverUserId;
    private String week;
    private List<SpeedPerDay> speedPerDay;
    private List<ArrivalTime> arrivalTimes;
    private IncidentSummary incidentSummary;

    public DriverDashboard(Long driverUserId, String week, List<SpeedPerDay> speedPerDay, List<ArrivalTime> arrivalTimes, IncidentSummary incidentSummary) {
        this.driverUserId = driverUserId;
        this.week = week;
        this.speedPerDay = speedPerDay;
        this.arrivalTimes = arrivalTimes;
        this.incidentSummary = incidentSummary;
    }

    public record SpeedPerDay(String day, double averageSpeed) {}
    public record ArrivalTime(String day, String time) {}

    public Long getDriverUserId() { return driverUserId; }
    public String getWeek() { return week; }
    public List<SpeedPerDay> getSpeedPerDay() { return speedPerDay; }
    public List<ArrivalTime> getArrivalTimes() { return arrivalTimes; }
    public IncidentSummary getIncidentSummary() { return incidentSummary; }
}
