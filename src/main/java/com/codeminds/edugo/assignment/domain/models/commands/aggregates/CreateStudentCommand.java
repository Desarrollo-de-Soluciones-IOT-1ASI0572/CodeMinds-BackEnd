package com.codeminds.edugo.assignment.domain.models.commands.aggregates;

public record CreateStudentCommand (
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        Long parentProfileId,
        Long driverId
) {}