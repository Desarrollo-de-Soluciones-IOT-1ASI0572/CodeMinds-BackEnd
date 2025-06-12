package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getUserId(), entity.getFullName(),
                entity.getEmail(), entity.getMobileNumber(), entity.getAddress(), entity.getGender(),
                entity.getPhotoUrl(), entity.getRole());
    }
}
