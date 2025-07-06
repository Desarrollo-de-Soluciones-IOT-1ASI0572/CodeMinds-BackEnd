package com.codeminds.edugo.assignments.interfaces.rest.resources;

public record StudentResource(
        Long id,
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        WristbandResource wristband,
        Long parentProfile,
        Long driverId
) {}