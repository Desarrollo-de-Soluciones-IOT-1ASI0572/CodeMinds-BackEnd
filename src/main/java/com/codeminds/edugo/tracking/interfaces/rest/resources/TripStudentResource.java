package com.codeminds.edugo.tracking.interfaces.rest.resources;

import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentDto;

import java.time.LocalDateTime;

public record TripStudentResource(
        Long id,
        StudentDto student,
        boolean attended,
        LocalDateTime boardedAt,
        LocalDateTime exitedAt
) {}