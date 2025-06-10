package com.codeminds.edugo.profiles.domain.services;

import java.util.Optional;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.commands.CreateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.UpdateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.entities.Driver;

public interface DriverCommandService {
    Long handle(CreateDriverCommand command, User user);

    Optional<Driver> handle(UpdateDriverCommand command);

    void handle(DeleteDriverCommand command);
}
