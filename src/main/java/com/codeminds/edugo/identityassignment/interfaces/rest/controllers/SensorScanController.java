package com.codeminds.edugo.identityassignment.interfaces.rest.controllers;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteSensorScanCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.sensorscan.GetAllSensorScansQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.sensorscan.GetSensorScansByIdQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.sensorscan.GetSensorScansByWristbandIdQuery;
import com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan.SensorScanCommandService;
import com.codeminds.edugo.identityassignment.domain.services.entities.sensorscan.SensorScanQueryService;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan.CreateSensorScanResource;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan.SensorScanResource;
import com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.sensorscan.CreateSensorScanCommandFromResourceAssembler;
import com.codeminds.edugo.identityassignment.interfaces.rest.transform.entities.sensorscan.SensorScanResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/sensor-scans")
@Tag(name = "SensorScan", description = "Sensor Scans Endpoint")
public class SensorScanController {
    private final SensorScanCommandService sensorScanCommandService;
    private final SensorScanQueryService sensorScanQueryService;

    public SensorScanController(SensorScanCommandService sensorScanCommandService, SensorScanQueryService sensorScanQueryService) {
        this.sensorScanCommandService = sensorScanCommandService;
        this.sensorScanQueryService = sensorScanQueryService;
    }

    @PostMapping
    public ResponseEntity<List<SensorScanResource>> createSensorScan(
            @RequestBody CreateSensorScanResource resource) {
        Optional<SensorScan> sensorScan = sensorScanCommandService
                .handle(CreateSensorScanCommandFromResourceAssembler.toCommandFromResource(resource));

        return sensorScan.map(s ->
                        new ResponseEntity<>(
                                List.of(SensorScanResourceFromEntityAssembler.toResourceFromEntity(s)),
                                CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<SensorScanResource>> getAllSensorScans() {
        var getAllSensorScans = new GetAllSensorScansQuery();
        var sensorScans = sensorScanQueryService.handle(getAllSensorScans);
        var sensorScanResources = sensorScans.stream()
                .map(SensorScanResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sensorScanResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorScanResource> getSensorScanById(@PathVariable Long id) {
        return sensorScanQueryService.handle(new GetSensorScansByIdQuery(id))
                .stream()
                .findFirst()
                .map(SensorScanResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/wristband/{wristbandId}")
    public ResponseEntity<List<SensorScanResource>> getSensorScansByWristbandId(@PathVariable Long wristbandId) {
        var sensorScans = sensorScanQueryService.handle(new GetSensorScansByWristbandIdQuery(wristbandId));
        var sensorScanResources = sensorScans.stream()
                .map(SensorScanResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sensorScanResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorScan(@PathVariable Long id) {
        sensorScanCommandService.handle(new DeleteSensorScanCommand(id));
        return ResponseEntity.noContent().build();
    }
}
