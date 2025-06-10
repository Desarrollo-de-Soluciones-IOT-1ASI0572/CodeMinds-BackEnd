package com.codeminds.edugo.profiles.domain.exceptions;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long id) {
        super("Profile with ID " + id + " not found");
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
