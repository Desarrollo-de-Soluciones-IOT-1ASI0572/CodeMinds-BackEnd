package com.codeminds.edugo.tracking.domain.model.commands;

import java.time.LocalDateTime;

public record RegisterStudentBoardingCommand(
        Long tripId,
        Long studentId,
        LocalDateTime boardedAt
) {}
