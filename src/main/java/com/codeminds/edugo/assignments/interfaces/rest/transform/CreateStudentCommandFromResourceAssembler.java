package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.commands.CreateStudentCommand;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource){
        return new CreateStudentCommand(
                resource.name(),
                resource.lastName(),
                resource.homeAddress(),
                resource.schoolAddress(),
                resource.studentPhotoUrl(),
                resource.parentProfileId(),
                resource.driverId());
    }
}
