package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.RegisterStudentExitCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.RegisterStudentExitResource;

public class RegisterStudentExitCommandFromResourceAssembler {
    public static RegisterStudentExitCommand toCommandFromResource(RegisterStudentExitResource resource) {
        return new RegisterStudentExitCommand(
                resource.tripId(),
                resource.studentId(),
                resource.exitedAt()
        );
    }
}