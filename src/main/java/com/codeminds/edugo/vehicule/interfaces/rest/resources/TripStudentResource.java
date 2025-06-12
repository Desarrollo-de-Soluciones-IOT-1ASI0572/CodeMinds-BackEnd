package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.time.LocalDateTime;

public record TripStudentResource(
        Long id,
        Long studentId,
        boolean attended,
        LocalDateTime boardedAt,
        LocalDateTime exitedAt
) {}