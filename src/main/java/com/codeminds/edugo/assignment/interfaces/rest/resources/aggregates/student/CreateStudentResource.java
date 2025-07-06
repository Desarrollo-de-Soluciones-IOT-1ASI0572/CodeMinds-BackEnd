package com.codeminds.edugo.assignment.interfaces.rest.resources.aggregates.student;

public record CreateStudentResource(
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        Long parentProfileId,
        Long driverId
) {}
