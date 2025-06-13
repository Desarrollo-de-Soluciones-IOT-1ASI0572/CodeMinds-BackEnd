package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.fullName(), resource.email(), resource.mobileNumber(),
                resource.address(), resource.gender(), resource.photoUrl());
    }
}
