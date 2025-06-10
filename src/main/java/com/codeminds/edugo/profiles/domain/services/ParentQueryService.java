package com.codeminds.edugo.profiles.domain.services;

import java.util.List;
import java.util.Optional;

import com.codeminds.edugo.profiles.domain.model.entities.Parent;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllParentsQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByUserIdQuery;

public interface ParentQueryService {
    Optional<Parent> handle(GetParentByIdQuery query);

    Optional<Parent> handle(GetParentByUserIdQuery query);

    List<Parent> handle(GetAllParentsQuery query);

}
