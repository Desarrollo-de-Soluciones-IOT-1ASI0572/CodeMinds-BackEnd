package com.codeminds.edugo.iam.interfaces.rest.transform;

import com.codeminds.edugo.iam.domain.model.commands.SignInCommand;
import com.codeminds.edugo.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}