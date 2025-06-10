package com.codeminds.edugo.profiles.domain.services;

import java.util.List;
import java.util.Optional;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllProfilesQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserIdQuery;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);

    Optional<Profile> handle(GetProfileByUserIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}
