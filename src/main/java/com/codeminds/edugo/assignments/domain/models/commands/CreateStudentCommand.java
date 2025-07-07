package com.codeminds.edugo.assignments.domain.models.commands;

public record CreateStudentCommand (
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        Long parentProfileId,
        Long driverId
) {}