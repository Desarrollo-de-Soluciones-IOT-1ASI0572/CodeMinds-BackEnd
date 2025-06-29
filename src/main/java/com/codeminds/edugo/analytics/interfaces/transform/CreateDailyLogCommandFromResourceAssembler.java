package com.codeminds.edugo.analytics.interfaces.transform;

import com.codeminds.edugo.analytics.domain.model.commands.CreateDailyLogCommand;
import com.codeminds.edugo.analytics.interfaces.resources.CreateDailyLogResource;

public class CreateDailyLogCommandFromResourceAssembler {
    public static CreateDailyLogCommand toCommandFromResource(CreateDailyLogResource resource) {
        return new CreateDailyLogCommand(
                resource.driverUserId(),
                resource.date(),
                resource.arrivalTimeAtSchool(),
                resource.returnTimeHome(),
                resource.speed(),
                resource.detour(),
                resource.lateness(),
                resource.speeding()
        );
    }
}
