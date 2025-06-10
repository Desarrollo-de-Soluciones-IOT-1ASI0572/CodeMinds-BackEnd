package com.codeminds.edugo.profiles.application.internal.outboundservices.acl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.iam.interfaces.acl.IamContextFacade;

@Service
public class ExternalUserService {
    private final IamContextFacade userContextFacade;

    public ExternalUserService(IamContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public Optional<User> fetchUserById(Long userId) {
        return userContextFacade.fetchUserById(userId);
    }
}
