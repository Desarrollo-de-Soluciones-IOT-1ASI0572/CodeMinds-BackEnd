package com.codeminds.edugo.iam.interfaces.rest.transform;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token, String role) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token, role);
    }
}