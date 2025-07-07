package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.RegisterStudentExitCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.RegisterStudentExitResource;

public class RegisterStudentExitCommandFromResourceAssembler {
    public static RegisterStudentExitCommand toCommandFromResource(RegisterStudentExitResource resource) {
        return new RegisterStudentExitCommand(
                resource.tripId(),
                resource.studentId(),
                resource.exitedAt()
        );
    }
}