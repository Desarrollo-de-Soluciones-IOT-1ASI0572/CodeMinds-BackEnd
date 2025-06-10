package com.codeminds.edugo.profiles.domain.services;

import java.util.List;
import java.util.Optional;

import com.codeminds.edugo.profiles.domain.model.entities.Driver;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllDriversQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByUserIdQuery;

public interface DriverQueryService {
    Optional<Driver> handle(GetDriverByIdQuery query);

    Optional<Driver> handle(GetDriverByUserIdQuery query);

    List<Driver> handle(GetAllDriversQuery query);
}
