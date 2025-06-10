package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.userId(),
                resource.fullName(),
                resource.email(),
                resource.phone_number(),
                resource.gender(),
                resource.profile_picture_url());
    }
}
