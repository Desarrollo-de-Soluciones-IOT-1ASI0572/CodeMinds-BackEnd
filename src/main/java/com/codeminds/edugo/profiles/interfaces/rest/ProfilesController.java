package com.codeminds.edugo.profiles.interfaces.rest;

import com.codeminds.edugo.profiles.domain.model.queries.GetAllProfilesQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserId;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfilesByRole;
import com.codeminds.edugo.profiles.domain.services.ProfileCommandService;
import com.codeminds.edugo.profiles.domain.services.ProfileQueryService;
import com.codeminds.edugo.profiles.interfaces.rest.resources.CreateProfileResource;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ProfileResource;
import com.codeminds.edugo.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.codeminds.edugo.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ProfilesController
 * <p>
 * This class is the entry point for all the REST endpoints related to the
 * Profile entity.
 * </p>
 */

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfilesController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    /**
     * Creates a new Profile
     * 
     * @param resource the resource containing the data to create the Profile
     * @return the created Profile
     */
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    /**
     * Gets a Profile by its id
     * 
     * @param profileId the id of the Profile to get
     * @return the Profile resource associated to given Profile id
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    /**
     * Gets all the Profiles
     * 
     * @return a list of all the Profile resources currently stored
     */
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResource> getProfileByUserId(@PathVariable Long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserId(userId);
        var profile = profileQueryService.handle(getProfileByUserIdQuery);
        if (profile.isEmpty())
            return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<ProfileResource>> getProfilesByRole(@PathVariable String role) {
        var getProfilesByRoleQuery = new GetProfilesByRole(role);
        var profiles = profileQueryService.handle(getProfilesByRoleQuery);
        var profileResources = profiles.stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }
}
