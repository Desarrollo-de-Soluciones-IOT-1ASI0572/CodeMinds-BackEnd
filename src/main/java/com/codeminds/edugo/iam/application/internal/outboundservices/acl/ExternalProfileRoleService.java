package com.codeminds.edugo.iam.application.internal.outboundservices.acl;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.interfaces.acl.ProfilesContextFacade;

import org.springframework.stereotype.Service;

@Service
public class ExternalProfileRoleService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileRoleService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Long createDriver(Long userId, User user) {
        return profilesContextFacade.createDriver(userId, user);
    }

    public Long createParent(Long userId, User user) {
        return profilesContextFacade.createParent(userId, user);
    }

}
