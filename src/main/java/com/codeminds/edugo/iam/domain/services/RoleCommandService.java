package com.codeminds.edugo.iam.domain.services;

import com.codeminds.edugo.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}