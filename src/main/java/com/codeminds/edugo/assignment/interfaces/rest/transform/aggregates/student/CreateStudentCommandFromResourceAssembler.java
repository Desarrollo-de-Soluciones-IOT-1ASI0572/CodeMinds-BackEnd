package com.codeminds.edugo.assignment.interfaces.rest.transform.aggregates.student;

import com.codeminds.edugo.assignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.assignment.interfaces.rest.resources.aggregates.student.CreateStudentResource;

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
