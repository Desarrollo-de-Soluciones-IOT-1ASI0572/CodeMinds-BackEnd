package com.codeminds.edugo.identityassignment.infrastructure.api.rest.dtos;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;

public record StudentResponseDTO(
    Long id,
    String name,
    String lastName,
    String homeAddress,
    String schoolAddress,
    String studentPhotoUrl
) {
    public static StudentResponseDTO fromDomain(Student student) {
        return new StudentResponseDTO(
            student.getId(),
            student.getName(),
            student.getLastName(),
            student.getHomeAddress(),
            student.getSchoolAddress(),
            student.getStudentPhotoUrl()
        );
    }
} 