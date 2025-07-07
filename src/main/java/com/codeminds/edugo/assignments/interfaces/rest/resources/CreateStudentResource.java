package com.codeminds.edugo.assignments.interfaces.rest.resources;

public record CreateStudentResource(
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        Long parentProfileId,
        Long driverId
) {}
