package com.codeminds.edugo.assignments.interfaces.rest;

import com.codeminds.edugo.assignments.domain.models.commands.DeleteWristbandCommand;
import com.codeminds.edugo.assignments.domain.models.entities.Wristband;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllWristbandsQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByWristbandStatusQuery;
import com.codeminds.edugo.assignments.domain.services.WristbandCommandService;
import com.codeminds.edugo.assignments.domain.services.WristbandQueryService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.WristbandRepository;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateWristbandResource;
import com.codeminds.edugo.assignments.interfaces.rest.resources.WristbandResource;

import com.codeminds.edugo.assignments.interfaces.rest.transform.CreateWristbandCommandFromResourceAssembler;
import com.codeminds.edugo.assignments.interfaces.rest.transform.WristbandResourceFromEntityAssembler;
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
        var wristbandStatus = com.codeminds.edugo.assignments.domain.models.valueobjects.WristbandStatus
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
