package com.codeminds.edugo.iam.domain.services;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.iam.domain.model.queries.GetAllUsersQuery;
import com.codeminds.edugo.iam.domain.model.queries.GetUserByIdQuery;
import com.codeminds.edugo.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByIdQuery query);

    Optional<User> handle(GetUserByUsernameQuery query);
}