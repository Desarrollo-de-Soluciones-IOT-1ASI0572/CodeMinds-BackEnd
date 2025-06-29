package com.codeminds.edugo.profiles.interfaces.acl;

import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.services.ProfileCommandService;
import com.codeminds.edugo.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

/**
 * Service Facade for the Profile context.
 *
 * <p>
 * It is used by the other contexts to interact with the Profile context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by
 * other contexts.
 * </p>
 *
 */
@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Creates a new Profile
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param street    the street address
     * @param number    the number
     * @param city      the city
     * @param state     the state
     * @param zipCode   the zip code
     * @return the profile id
     */
    public Long createProfile(String fullName, String email, String mobileNumber, String address, String gender,
            String photoUrl) {
        var createProfileCommand = new CreateProfileCommand(fullName, email, mobileNumber, address, gender, photoUrl);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty())
            return 0L;
        return profile.get().getId();
    }

    /**
     * Fetches the profile id by email
     *
     * @param email the email
     * @return the profile id
     */
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(email);
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty())
            return 0L;
        return profile.get().getId();

    }

    public String fetchProfileRoleById(Long profileId) {
        var query = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(query);
        if (profile.isEmpty())
            throw new IllegalArgumentException("Profile with id " + profileId + " does not exist");

        return profile.get().getRole();
    }
}