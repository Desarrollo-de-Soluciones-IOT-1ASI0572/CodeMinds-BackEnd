package com.codeminds.edugo.profiles.application.internal.outboundservices.acl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.iam.interfaces.acl.IamContextFacade;

@Service
public class ExternalIAMService {

    private final IamContextFacade iamContextFacade;

    public ExternalIAMService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<Long> fetchUserIdByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        Long userId = iamContextFacade.fetchUserIdByUsername(username);
        if (userId == 0L)
            return Optional.empty();
        return Optional.of(userId);
    }

    public Optional<String> fetchUserRoleByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        String role = iamContextFacade.fetchUserRoleByUsername(username);
        if (role == null || role.isEmpty())
            return Optional.empty();
        return Optional.of(role);
    }

}
