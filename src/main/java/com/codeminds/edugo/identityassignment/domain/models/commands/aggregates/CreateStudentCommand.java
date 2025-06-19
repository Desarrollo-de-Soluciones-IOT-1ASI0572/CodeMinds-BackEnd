package com.codeminds.edugo.identityassignment.domain.models.commands.aggregates;

public record CreateStudentCommand (
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        Long parentProfileId
) {}