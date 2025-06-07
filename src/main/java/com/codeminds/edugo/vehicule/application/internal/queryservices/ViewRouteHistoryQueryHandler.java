package com.codeminds.edugo.vehicule.application.internal.queryservices;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.queries.ViewRouteHistoryQuery;
import com.codeminds.edugo.vehicule.domain.services.TrackingQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewRouteHistoryQueryHandler {
    private final TrackingQueryService trackingService;

    public ViewRouteHistoryQueryHandler(TrackingQueryService trackingService) {
        this.trackingService = trackingService;
    }

    public List<Location> handle(ViewRouteHistoryQuery query) {
        return trackingService.handle(query);
    }
}
