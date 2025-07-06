package com.codeminds.edugo.tracking.interfaces.rest.resources;

import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentDto;

import java.time.LocalDateTime;
import java.util.List;

public record ActiveTripResource(
        Long id,
        LocalDateTime startTime,
        List<StudentDto> students
) {}
