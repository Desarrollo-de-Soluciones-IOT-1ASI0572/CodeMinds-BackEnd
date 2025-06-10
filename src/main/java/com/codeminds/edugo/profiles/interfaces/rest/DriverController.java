package com.codeminds.edugo.profiles.interfaces.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.codeminds.edugo.profiles.domain.model.commands.DeleteDriverCommand;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllDriversQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByUserIdQuery;
import com.codeminds.edugo.profiles.domain.services.DriverCommandService;
import com.codeminds.edugo.profiles.domain.services.DriverQueryService;
import com.codeminds.edugo.profiles.interfaces.rest.resources.DriverResource;
import com.codeminds.edugo.profiles.interfaces.rest.transform.DriverResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;

@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/drivers", produces = "application/json")
@Tag(name = "Drivers", description = "Driver management API")
public class DriverController {
    private final DriverCommandService driverCommandService;
    private final DriverQueryService driverQueryService;

    public DriverController(DriverCommandService driverCommandService, DriverQueryService driverQueryService) {
        this.driverCommandService = driverCommandService;
        this.driverQueryService = driverQueryService;
    }

    @GetMapping
    public ResponseEntity<List<DriverResource>> getAllDrivers() {
        var query = new GetAllDriversQuery();
        var drivers = driverQueryService.handle(query);
        var resources = drivers.stream().map(DriverResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResource> getDriverById(@PathVariable Long id) {
        var query = new GetDriverByIdQuery(id);
        var driver = driverQueryService.handle(query);
        if (driver.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<DriverResource> getDriverByUserId(@RequestParam Long userId) {
        var query = new GetDriverByUserIdQuery(userId);
        var driver = driverQueryService.handle(query);
        if (driver.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id) {
        var command = new DeleteDriverCommand(id);
        try {
            driverCommandService.handle(command);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok("Conductor con id " + id + " eliminado exitosamente");
    }
}
