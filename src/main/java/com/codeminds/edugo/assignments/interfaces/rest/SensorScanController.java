package com.codeminds.edugo.assignments.interfaces.rest;

import com.codeminds.edugo.assignments.domain.models.commands.DeleteSensorScanCommand;
import com.codeminds.edugo.assignments.domain.models.entities.SensorScan;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllSensorScansQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetSensorScansByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetSensorScansByWristbandIdQuery;
import com.codeminds.edugo.assignments.domain.services.SensorScanCommandService;
import com.codeminds.edugo.assignments.domain.services.SensorScanQueryService;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateSensorScanResource;
import com.codeminds.edugo.assignments.interfaces.rest.resources.SensorScanResource;
import com.codeminds.edugo.assignments.interfaces.rest.transform.CreateSensorScanCommandFromResourceAssembler;
import com.codeminds.edugo.assignments.interfaces.rest.transform.SensorScanResourceFromEntityAssembler;
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
