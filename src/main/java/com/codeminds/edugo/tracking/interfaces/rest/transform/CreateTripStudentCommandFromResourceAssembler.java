package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.CreateTripStudentCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.CreateTripStudentResource;

public class CreateTripStudentCommandFromResourceAssembler {
    public static CreateTripStudentCommand toCommandFromResource(CreateTripStudentResource resource) {
        return new CreateTripStudentCommand(resource.tripId(), resource.studentId());
    }
}