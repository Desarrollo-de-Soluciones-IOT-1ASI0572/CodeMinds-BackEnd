package com.codeminds.edugo.profiles.domain.exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(Long id) {
        super("Driver with ID " + id + " not found");
    }

    public DriverNotFoundException(String message) {
        super(message);
    }

}
