package com.codeminds.edugo.vehicule.domain.services;

import com.codeminds.edugo.vehicule.domain.model.entities.Location;
import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import com.codeminds.edugo.vehicule.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TrackingQueryService {
    Optional<Location> handle(GetCurrentLocationQuery query);

    List<Location> handle(ViewRouteHistoryQuery query);

    Optional<Trip> handle(GetActiveTripQuery query);

    List<Trip> handle(GetPastTripsByDriverQuery query);

    List<TripStudent> handle(GetTripStudentsQuery query);

    List<Location> handle(GetLocationsByTripIdQuery query);
}
