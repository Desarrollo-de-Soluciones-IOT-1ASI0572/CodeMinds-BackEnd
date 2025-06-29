package com.codeminds.edugo.iam.interfaces.rest.transform;

import com.codeminds.edugo.iam.domain.model.entities.Role;
import com.codeminds.edugo.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}