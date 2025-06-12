package com.codeminds.edugo.profiles.domain.services;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllProfilesQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserId;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfilesByRole;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);

    Optional<Profile> handle(GetProfileByUserId query);

    List<Profile> handle(GetProfilesByRole query);
}
