package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.commands.CreateWristbandCommand;
import com.codeminds.edugo.assignments.domain.models.commands.DeleteWristbandCommand;
import com.codeminds.edugo.assignments.domain.models.entities.Wristband;

import java.util.Optional;

public interface WristbandCommandService {
    Optional<Wristband> handle(CreateWristbandCommand command);
    void handle(DeleteWristbandCommand command);
}
