package com.codeminds.edugo.profiles.application.internal.queryservices;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllProfilesQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserId;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfilesByRole;
import com.codeminds.edugo.profiles.domain.services.ProfileQueryService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.email());
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileByUserId query) {
        return profileRepository.findByUserId(query.userId());
    }

    @Override
    public List<Profile> handle(GetProfilesByRole query) {
        return profileRepository.findByRole(query.role());
    }
}
