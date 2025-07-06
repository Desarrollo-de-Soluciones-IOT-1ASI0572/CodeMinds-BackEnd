package com.codeminds.edugo.tracking.interfaces.rest.resources;

import java.time.LocalDateTime;

public record RegisterStudentBoardingResource(Long tripId, Long studentId, LocalDateTime boardedAt) {}