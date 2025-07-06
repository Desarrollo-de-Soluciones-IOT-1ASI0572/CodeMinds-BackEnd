package com.codeminds.edugo.tracking.domain.model.commands;

public record CreateTripStudentCommand(
        Long tripId,
        Long studentId
) {}