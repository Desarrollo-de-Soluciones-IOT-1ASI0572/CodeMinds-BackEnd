package com.codeminds.edugo.tracking.domain.model.commands;

import java.time.LocalDateTime;

public record RegisterStudentExitCommand(
        Long tripId,
        Long studentId,
        LocalDateTime exitedAt
) {}
