package com.codeminds.edugo.vehicule.application.internal.queryservices;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.queries.GetCurrentLocationQuery;
import com.codeminds.edugo.vehicule.domain.services.TrackingQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCurrentLocationQueryHandler {
    private final TrackingQueryService trackingService;

    public GetCurrentLocationQueryHandler(TrackingQueryService trackingService) {
        this.trackingService = trackingService;
    }

    public Optional<Location> handle(GetCurrentLocationQuery query) {
        return trackingService.handle(query);
    }
}
