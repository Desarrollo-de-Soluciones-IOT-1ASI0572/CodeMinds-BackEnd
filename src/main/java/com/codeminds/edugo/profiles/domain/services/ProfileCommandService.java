package com.codeminds.edugo.profiles.domain.services;

import java.util.Optional;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteProfileCommand;
import com.codeminds.edugo.profiles.domain.model.commands.UpdateProfileCommand;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);

    Optional<Profile> handle(UpdateProfileCommand command);

    void handle(DeleteProfileCommand command);
}
