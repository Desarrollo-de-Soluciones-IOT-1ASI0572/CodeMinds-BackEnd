package com.codeminds.edugo.tracking.application.internal.queryservices;

import com.codeminds.edugo.tracking.domain.model.entities.Location;
import com.codeminds.edugo.tracking.domain.model.entities.Trip;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.domain.model.queries.*;
import com.codeminds.edugo.tracking.domain.services.TrackingQueryService;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.LocationRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripRepository;
import com.codeminds.edugo.tracking.infrastructure.persistance.jpa.repositories.TripStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingQueryServiceImpl implements TrackingQueryService {

    private final LocationRepository locationRepository;
    private final TripRepository tripRepository;
    private final TripStudentRepository tripStudentRepository;

    public TrackingQueryServiceImpl(LocationRepository locationRepository,
                                    TripRepository tripRepository,
                                    TripStudentRepository tripStudentRepository) {
        this.locationRepository = locationRepository;
        this.tripRepository = tripRepository;
        this.tripStudentRepository = tripStudentRepository;
    }

    @Override
    public Optional<Location> handle(GetCurrentLocationQuery query) {
        return locationRepository.findLastLocation(query.vehicleId());
    }

    @Override
    public List<Location> handle(ViewRouteHistoryQuery query) {
        return locationRepository.findByVehicleIdAndTimestampBetweenOrderByTimestampAsc(
                query.vehicleId(), query.startDate(), query.endDate()
        );
    }

    @Override
    public Optional<Trip> handle(GetActiveTripQuery query) {
        return tripRepository.findByVehicle_IdAndEndTimeIsNull(query.vehicleId());
    }

    @Override
    public List<Trip> handle(GetPastTripsByDriverQuery query) {
        return tripRepository.findByVehicle_DriverIdAndEndTimeIsNotNull(query.driverId());
    }

    @Override
    public List<TripStudent> handle(GetTripStudentsQuery query) {
        return tripStudentRepository.findByTrip_Id(query.tripId());
    }

    @Override
    public List<Location> handle(GetLocationsByTripIdQuery query) {
        return locationRepository.findByTripIdOrderByTimestampAsc(query.tripId());
    }
}
