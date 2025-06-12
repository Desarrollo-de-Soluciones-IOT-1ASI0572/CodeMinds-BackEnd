package com.codeminds.edugo.vehicule.domain.model.commands;

public record CreateTripStudentCommand(
        Long tripId,
        Long studentId
) {}