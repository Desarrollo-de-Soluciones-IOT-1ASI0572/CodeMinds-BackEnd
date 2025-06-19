package com.codeminds.edugo.identityassignment.interfaces.rest.transform.aggregates.student;

import com.codeminds.edugo.identityassignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource){
        return new CreateStudentCommand(resource.name(),
                resource.lastName(),
                resource.homeAddress(),
                resource.schoolAddress(),
                resource.StudentPhotoUrl());
    }
}
