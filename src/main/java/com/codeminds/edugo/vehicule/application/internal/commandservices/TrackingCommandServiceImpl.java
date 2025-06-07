package com.codeminds.edugo.vehicule.application.internal.commandservices;

import com.codeminds.edugo.vehicule.domain.model.aggregates.Vehicle;
import com.codeminds.edugo.vehicule.domain.model.commands.ReportSpeedCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.StartRouteCommand;
import com.codeminds.edugo.vehicule.domain.model.commands.UpdateLocationCommand;
import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.services.TrackingCommandService;
import com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories.LocationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingCommandServiceImpl implements TrackingCommandService {

    private final ReportSpeedCommandHandler reportSpeedHandler;
    private final UpdateLocationCommandHandler updateLocationHandler;
    private final StartRouteCommandHandler startRouteHandler;

    public TrackingCommandServiceImpl(
            ReportSpeedCommandHandler reportSpeedHandler,
            UpdateLocationCommandHandler updateLocationHandler,
            StartRouteCommandHandler startRouteHandler
    ) {
        this.reportSpeedHandler = reportSpeedHandler;
        this.updateLocationHandler = updateLocationHandler;
        this.startRouteHandler = startRouteHandler;
    }

    @Override
    public Optional<Boolean> handle(ReportSpeedCommand command) {
        return reportSpeedHandler.handle(command);
    }

    @Override
    public Optional<Location> handle(UpdateLocationCommand command) {
        return updateLocationHandler.handle(command);
    }

    @Override
    public Optional<Vehicle> handle(StartRouteCommand command) {
        return startRouteHandler.handle(command);
    }
}
