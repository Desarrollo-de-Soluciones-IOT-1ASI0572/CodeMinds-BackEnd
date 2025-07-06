package com.codeminds.edugo.assignment.domain.services.entities.wristband;

import com.codeminds.edugo.assignment.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.assignment.domain.models.commands.entities.DeleteWristbandCommand;
import com.codeminds.edugo.assignment.domain.models.entities.Wristband;

import java.util.Optional;

public interface WristbandCommandService {
    Optional<Wristband> handle(CreateWristbandCommand command);
    void handle(DeleteWristbandCommand command);
}
