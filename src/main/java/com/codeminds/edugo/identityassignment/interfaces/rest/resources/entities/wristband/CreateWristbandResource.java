package com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.wristband;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;

public record CreateWristbandResource(
        String rfidCode,
        WristbandStatus wristbandStatus,
        Student student
) {}
