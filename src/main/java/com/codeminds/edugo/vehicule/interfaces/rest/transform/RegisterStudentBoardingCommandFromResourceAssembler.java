package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.commands.RegisterStudentBoardingCommand;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.RegisterStudentBoardingResource;

public class RegisterStudentBoardingCommandFromResourceAssembler {
    public static RegisterStudentBoardingCommand toCommandFromResource(RegisterStudentBoardingResource resource) {
        return new RegisterStudentBoardingCommand(
                resource.tripId(),
                resource.studentId(),
                resource.boardedAt()
        );
    }
}