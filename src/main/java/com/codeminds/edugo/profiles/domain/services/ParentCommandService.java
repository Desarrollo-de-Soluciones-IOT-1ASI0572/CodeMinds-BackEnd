package com.codeminds.edugo.profiles.domain.services;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.commands.CreateParentCommand;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteParentCommand;

public interface ParentCommandService {
    Long handle(CreateParentCommand command, User user);

    void handle(DeleteParentCommand command);

}
