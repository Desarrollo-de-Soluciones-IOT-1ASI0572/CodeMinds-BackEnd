package com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;

public record StudentResource(
        Long id,
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String StudentPhotoUrl,
        Wristband wristband
) {}
