package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(
                profile.getId(),
                profile.getUserId(),
                profile.getFull_name(),
                profile.getEmail(),
                profile.getPhone_number(),
                profile.getGender(),
                profile.getProfile_picture_url());
    }
}
