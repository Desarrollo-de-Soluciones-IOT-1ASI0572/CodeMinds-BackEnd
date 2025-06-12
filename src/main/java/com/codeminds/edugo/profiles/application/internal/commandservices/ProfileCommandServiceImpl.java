package com.codeminds.edugo.profiles.application.internal.commandservices;

import com.codeminds.edugo.profiles.application.internal.outboundservices.acl.ExternalIAMService;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.domain.services.ProfileCommandService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final ExternalIAMService externalIAMService;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, ExternalIAMService externalIAMService) {
        this.profileRepository = profileRepository;
        this.externalIAMService = externalIAMService;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = command.email();
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });
        var userId = externalIAMService.fetchUserIdByUsername(command.email());
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("User with email " + command.email() + " does not exist");
        }
        var role = externalIAMService.fetchUserRoleByUsername(command.email());
        if (role.isEmpty()) {
            throw new IllegalArgumentException("Role for user with email " + command.email() + " does not exist");
        }
        var profile = new Profile(command, userId.get(), role.get());
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}
