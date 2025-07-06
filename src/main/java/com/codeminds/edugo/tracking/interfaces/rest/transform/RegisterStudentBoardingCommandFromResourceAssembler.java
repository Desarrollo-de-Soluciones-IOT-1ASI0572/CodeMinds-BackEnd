package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.tracking.domain.model.commands.RegisterStudentBoardingCommand;
import com.codeminds.edugo.tracking.interfaces.rest.resources.RegisterStudentBoardingResource;

public class RegisterStudentBoardingCommandFromResourceAssembler {
    public static RegisterStudentBoardingCommand toCommandFromResource(RegisterStudentBoardingResource resource) {
        return new RegisterStudentBoardingCommand(
                resource.tripId(),
                resource.studentId(),
                resource.boardedAt()
        );
    }
}