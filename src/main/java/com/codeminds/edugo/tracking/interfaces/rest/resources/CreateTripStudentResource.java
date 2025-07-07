package com.codeminds.edugo.tracking.interfaces.rest.resources;

public record CreateTripStudentResource(
        Long tripId,
        Long studentId
) {}