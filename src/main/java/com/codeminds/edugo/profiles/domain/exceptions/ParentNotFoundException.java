package com.codeminds.edugo.profiles.domain.exceptions;

public class ParentNotFoundException extends RuntimeException {
    public ParentNotFoundException(Long id) {
        super("Parent with ID " + id + " not found");
    }

    public ParentNotFoundException(String message) {
        super(message);
    }

}
