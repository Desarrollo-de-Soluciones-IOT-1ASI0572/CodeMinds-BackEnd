package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.commands.UpdateProfileCommand;
import com.codeminds.edugo.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long profileId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(
                profileId,
                resource.full_name(),
                resource.email(),
                resource.phone_number(),
                resource.gender(),
                resource.profile_picture_url());
    }
}
