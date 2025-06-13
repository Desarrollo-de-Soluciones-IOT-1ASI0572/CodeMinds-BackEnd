package com.codeminds.edugo.vehicule.domain.model.commands;

import java.time.LocalDateTime;

public record RegisterStudentBoardingCommand(
        Long tripId,
        Long studentId,
        LocalDateTime boardedAt
) {}
