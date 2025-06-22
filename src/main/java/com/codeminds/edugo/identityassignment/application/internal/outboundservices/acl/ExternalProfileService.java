package com.codeminds.edugo.identityassignment.application.internal.outboundservices.acl;

import com.codeminds.edugo.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

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
}
