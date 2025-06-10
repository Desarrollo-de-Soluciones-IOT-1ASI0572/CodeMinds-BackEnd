package com.codeminds.edugo.profiles.interfaces.rest.transform;

import com.codeminds.edugo.profiles.domain.model.entities.Parent;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ParentResource;

public class ParentResourceFromEntityAssembler {
    public static ParentResource toResourceFromEntity(Parent parent) {
        return new ParentResource(parent.getId(), parent.getUserId());
    }

}
