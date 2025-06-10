package com.codeminds.edugo.profiles.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllProfilesQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.codeminds.edugo.profiles.domain.services.ProfileQueryService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.ProfileRepository;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByUserIdQuery query) {
        return profileRepository.findByUser_Id(query.user_id());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
