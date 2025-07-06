package com.codeminds.edugo.assignment.interfaces.rest.controllers;

import com.codeminds.edugo.assignment.domain.models.commands.entities.DeleteWristbandCommand;
import com.codeminds.edugo.assignment.domain.models.entities.Wristband;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetAllWristbandsQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByWristbandStatusQuery;
import com.codeminds.edugo.assignment.domain.services.entities.wristband.WristbandCommandService;
import com.codeminds.edugo.assignment.domain.services.entities.wristband.WristbandQueryService;
import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband.CreateWristbandResource;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband.WristbandResource;

import com.codeminds.edugo.assignment.interfaces.rest.transform.entities.wristband.CreateWristbandCommandFromResourceAssembler;
import com.codeminds.edugo.assignment.interfaces.rest.transform.entities.wristband.WristbandResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/wristbands")
@Tag(name = "Wristband", description = "Wristbands Endpoint")
public class WristbandController {
    private final WristbandCommandService wristbandCommandService;
    private final WristbandQueryService wristbandQueryService;
    private final WristbandRepository wristbandRepository;

    public WristbandController(WristbandCommandService wristbandCommandService,
            WristbandQueryService wristbandQueryService, WristbandRepository wristbandRepository) {
        this.wristbandCommandService = wristbandCommandService;
        this.wristbandQueryService = wristbandQueryService;
        this.wristbandRepository = wristbandRepository;
    }

    @PostMapping
    public ResponseEntity<List<WristbandResource>> createWristband(
            @RequestBody CreateWristbandResource resource) {
        Optional<Wristband> wristband = wristbandCommandService
                .handle(CreateWristbandCommandFromResourceAssembler.toCommandFromResource(resource));

        return wristband.map(w -> new ResponseEntity<>(
                List.of(WristbandResourceFromEntityAssembler.toResourceFromEntity(w)),
                CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<WristbandResource>> getAllWristbands() {
        var getAllWristbands = new GetAllWristbandsQuery();
        var wristbands = wristbandQueryService.handle(getAllWristbands);
        var wristbandResources = wristbands.stream()
                .map(WristbandResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(wristbandResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WristbandResource> getWristbandById(@PathVariable Long id) {
        return wristbandQueryService.handle(new GetWristbandsByIdQuery(id))
                .stream()
                .findFirst()
                .map(WristbandResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<WristbandResource>> getWristbandsByStudentId(@PathVariable Long studentId) {
        var wristbands = wristbandQueryService.handle(new GetWristbandsByStudentIdQuery(studentId));
        var wristbandResources = wristbands.stream()
                .map(WristbandResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(wristbandResources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<WristbandResource>> getWristbandsByStatus(@PathVariable String status) {
        var wristbandStatus = com.codeminds.edugo.assignment.domain.models.valueobjects.WristbandStatus
                .valueOf(status.toUpperCase());
        var wristbands = wristbandQueryService.handle(new GetWristbandsByWristbandStatusQuery(wristbandStatus));
        var wristbandResources = wristbands.stream()
                .map(WristbandResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(wristbandResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWristband(@PathVariable Long id) {
        wristbandCommandService.handle(new DeleteWristbandCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rfid/{rfidCode}")
    public ResponseEntity<WristbandResource> getWristbandByRfidCode(@PathVariable String rfidCode) {
        return wristbandRepository.findByRfidCode(rfidCode)
                .map(WristbandResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
