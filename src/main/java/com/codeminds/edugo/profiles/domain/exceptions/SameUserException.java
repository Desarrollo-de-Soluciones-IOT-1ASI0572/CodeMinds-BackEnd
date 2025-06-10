package com.codeminds.edugo.profiles.domain.exceptions;

public class SameUserException extends RuntimeException {
    public SameUserException(Long userId) {
        super("Profile with user " + userId + " already exists");
    }

    public SameUserException(String message) {
        super(message);
    }
}
