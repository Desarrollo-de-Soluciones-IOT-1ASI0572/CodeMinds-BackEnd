package com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student;

public record CreateStudentResource(
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String StudentPhotoUrl
) {}
