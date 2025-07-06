package com.codeminds.edugo.tracking.interfaces.rest.resources;

import java.time.LocalDateTime;

public record RegisterStudentExitResource(Long tripId, Long studentId, LocalDateTime exitedAt) {}