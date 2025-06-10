package com.codeminds.edugo.profiles.interfaces.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.codeminds.edugo.profiles.domain.model.queries.GetAllParentsQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByUserIdQuery;
import com.codeminds.edugo.profiles.domain.services.ParentCommandService;
import com.codeminds.edugo.profiles.domain.services.ParentQueryService;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ParentResource;
import com.codeminds.edugo.profiles.interfaces.rest.transform.ParentResourceFromEntityAssembler;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteParentCommand;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/parents", produces = "application/json")
@Tag(name = "Parents", description = "Parent management API")
public class ParentController {

    private final ParentCommandService parentCommandService;
    private final ParentQueryService parentQueryService;

    public ParentController(ParentCommandService parentCommandService, ParentQueryService parentQueryService) {
        this.parentCommandService = parentCommandService;
        this.parentQueryService = parentQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ParentResource>> getAllParents() {
        var query = new GetAllParentsQuery();
        var parents = parentQueryService.handle(query);
        var resources = parents.stream().map(ParentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentResource> getParentById(@RequestParam Long id) {
        var query = new GetParentByIdQuery(id);
        var parent = parentQueryService.handle(query);
        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = ParentResourceFromEntityAssembler.toResourceFromEntity(parent.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<ParentResource> getParentByUserId(@RequestParam Long userId) {
        var query = new GetParentByUserIdQuery(userId);
        var parent = parentQueryService.handle(query);
        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resource = ParentResourceFromEntityAssembler.toResourceFromEntity(parent.get());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParent(@RequestParam Long id) {
        var command = new DeleteParentCommand(id);
        try {
            parentCommandService.handle(command);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok("Padre con id:" + id + " eliminado correctamente.");
    }
}
