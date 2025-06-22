package com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.wristband.WristbandResource;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ProfileResource;

public record StudentResource(
        Long id,
        String name,
        String lastName,
        String homeAddress,
        String schoolAddress,
        String studentPhotoUrl,
        WristbandResource wristband,
        ProfileResource parentProfile,  // ← Nuevo campo
        Long driverId
) {}