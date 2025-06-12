package com.codeminds.edugo.vehicule.interfaces.rest.resources;

import java.time.LocalDateTime;

public record RegisterStudentBoardingResource(Long tripId, Long studentId, LocalDateTime boardedAt) {}