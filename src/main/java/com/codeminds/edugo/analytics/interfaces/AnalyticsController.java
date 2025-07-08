package com.codeminds.edugo.analytics.interfaces;

import com.codeminds.edugo.analytics.domain.model.querys.GetAllDailyLogQuery;
import com.codeminds.edugo.analytics.domain.model.querys.GetAllDailyLogsByDriverIdQuery;
import com.codeminds.edugo.analytics.domain.model.querys.GetDailyLogByIdQuery;
import com.codeminds.edugo.analytics.domain.services.DailyLogCommandService;
import com.codeminds.edugo.analytics.domain.services.DailyLogQueryService;
import com.codeminds.edugo.analytics.interfaces.resources.CreateDailyLogResource;
import com.codeminds.edugo.analytics.interfaces.resources.DailyLogDashboardResource;
import com.codeminds.edugo.analytics.interfaces.resources.DailyLogResource;
import com.codeminds.edugo.analytics.interfaces.transform.CreateDailyLogCommandFromResourceAssembler;
import com.codeminds.edugo.analytics.interfaces.transform.DailyLogDashboardResourceFromEntityAssembler;
import com.codeminds.edugo.analytics.interfaces.transform.DailyLogResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Analytics", description = "Analytics Management Endpoints")
public class AnalyticsController {
    private final DailyLogQueryService dailyLogQueryService;
    private final DailyLogCommandService analyticsCommandService;

    public AnalyticsController(DailyLogQueryService dailyLogQueryService,
            DailyLogCommandService analyticsCommandService) {
        this.dailyLogQueryService = dailyLogQueryService;
        this.analyticsCommandService = analyticsCommandService;
    }

    @GetMapping("/logs")
    public ResponseEntity<List<DailyLogResource>> getAllLogs() {
        var getAllDailyLogsQuery = new GetAllDailyLogQuery();
        var dailyLogs = dailyLogQueryService.handle(getAllDailyLogsQuery);
        var dailyLogsResources = dailyLogs.stream().map(DailyLogResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(dailyLogsResources);
    }

    @GetMapping("/dashboard/{driverId}")
    public ResponseEntity<DailyLogDashboardResource> getDashboard(@PathVariable Long driverId) {
        var getDailyLogByIdQuery = new GetDailyLogByIdQuery(driverId);
        var dailyLogs = dailyLogQueryService.handle(getDailyLogByIdQuery);
        var dailyLogResource = DailyLogDashboardResourceFromEntityAssembler.toResourceFromEntity(dailyLogs.get());
        return ResponseEntity.ok(dailyLogResource);
    }

    @PostMapping
    public ResponseEntity<DailyLogResource> createDailyLog(@RequestBody CreateDailyLogResource resource) {
        var command = CreateDailyLogCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = analyticsCommandService.handle(command);
        if (result.isEmpty())
            return ResponseEntity.badRequest().build();

        var logResource = DailyLogResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return new ResponseEntity<>(logResource, HttpStatus.CREATED);
    }

    @GetMapping("/logs/driver/{driverId}")
    public ResponseEntity<List<DailyLogResource>> getLogsByDriverId(@PathVariable Long driverId) {
        var query = new GetAllDailyLogsByDriverIdQuery(driverId);
        var logs = dailyLogQueryService.handle(query);
        var resources = logs.stream().map(DailyLogResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }
}
