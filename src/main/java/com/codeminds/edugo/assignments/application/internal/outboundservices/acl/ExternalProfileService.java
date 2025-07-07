package com.codeminds.edugo.assignments.application.internal.outboundservices.acl;

import com.codeminds.edugo.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean isDriverProfile(Long profileId) {
        String role = profilesContextFacade.fetchProfileRoleById(profileId);
        return "ROLE_DRIVER".equalsIgnoreCase(role);
    }

    public void validateDriverProfile(Long profileId) {
        String role = getProfileRole(profileId);
        if (!"ROLE_DRIVER".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Profile with id " + profileId + " is not a valid driver");
        }
    }

    public void validateParentProfile(Long profileId) {
        String role = getProfileRole(profileId);
        if (!"ROLE_PARENT".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Profile with id " + profileId + " is not a valid parent");
        }
    }

    private String getProfileRole(Long profileId) {
        if (profileId == null || profileId <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }

        String role = profilesContextFacade.fetchProfileRoleById(profileId);
        if (!StringUtils.hasText(role)) {
            throw new IllegalStateException("Profile role is empty for profile ID: " + profileId);
        }
        return role;
    }
}
