package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentDto;

import java.time.LocalDateTime;
import java.util.List;

public record ActiveTripResource(
        Long id,
        LocalDateTime startTime,
        List<StudentDto> students
) {}
