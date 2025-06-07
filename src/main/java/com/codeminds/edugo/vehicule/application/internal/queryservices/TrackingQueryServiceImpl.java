package com.codeminds.edugo.vehicule.application.internal.queryservices;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.queries.GetCurrentLocationQuery;
import com.codeminds.edugo.vehicule.domain.model.queries.ViewRouteHistoryQuery;
import com.codeminds.edugo.vehicule.domain.services.TrackingQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingQueryServiceImpl implements TrackingQueryService {

    @Override
    public List<Location> handle(ViewRouteHistoryQuery query) {
        return List.of();
    }

    @Override
    public Optional<Location> handle(GetCurrentLocationQuery query) {
        return Optional.empty(); 
    }
}