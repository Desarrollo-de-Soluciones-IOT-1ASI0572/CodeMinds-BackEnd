package com.codeminds.edugo.vehicule.domain.services;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.queries.GetCurrentLocationQuery;
import com.codeminds.edugo.vehicule.domain.model.queries.ViewRouteHistoryQuery;

import java.util.List;
import java.util.Optional;

public interface TrackingQueryService {
    Optional<Location> handle(GetCurrentLocationQuery query);
    List<Location> handle(ViewRouteHistoryQuery query);
}
