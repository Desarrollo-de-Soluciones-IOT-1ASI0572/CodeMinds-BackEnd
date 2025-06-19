package com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student;

public record StudentDto(
        Long id,
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl
) {}
