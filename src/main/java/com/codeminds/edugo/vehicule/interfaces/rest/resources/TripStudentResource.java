package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentDto;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentResource;

import java.time.LocalDateTime;

public record TripStudentResource(
        Long id,
        StudentDto student,
        boolean attended,
        LocalDateTime boardedAt,
        LocalDateTime exitedAt
) {}