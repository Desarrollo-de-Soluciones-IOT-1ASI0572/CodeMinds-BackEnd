package com.codeminds.edugo.assignments.interfaces.rest.resources;

public record StudentDto(
        Long id,
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl
) {}
