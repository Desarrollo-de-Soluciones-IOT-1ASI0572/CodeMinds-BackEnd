package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.CreateTripStudentCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.CreateTripStudentResource;

public class CreateTripStudentCommandFromResourceAssembler {
    public static CreateTripStudentCommand toCommandFromResource(CreateTripStudentResource resource) {
        return new CreateTripStudentCommand(resource.tripId(), resource.studentId());
    }
}