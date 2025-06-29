package com.codeminds.edugo.realtimeoperationnotifications.application.internal.queryservices;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.queries.*;
import com.codeminds.edugo.realtimeoperationnotifications.domain.services.RealTimeNotificationQueryService;
import com.codeminds.edugo.realtimeoperationnotifications.infrastructure.persistence.jpa.repositories.RealTimeNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealTimeNotificationQueryServiceImpl implements RealTimeNotificationQueryService {

    private final RealTimeNotificationRepository realTimeNotificationRepository;

    public RealTimeNotificationQueryServiceImpl(RealTimeNotificationRepository realTimeNotificationRepository) {
        this.realTimeNotificationRepository = realTimeNotificationRepository;
    }

    @Override
    public List<RealTimeNotification> handle(GetAllRealTimeNotificationsQuery query) {
        return realTimeNotificationRepository.findAll();
    }

    @Override
    public List<RealTimeNotification> handle(GetRealNotificationsForUserType query) {
        return realTimeNotificationRepository.findByUserType(query.userType());
    }

    @Override
    public List<RealTimeNotification> handle(GetRealNotificationsForUserId query) {
        return realTimeNotificationRepository.findByUserId(query.userId());
    }

    @Override
    public List<RealTimeNotification> handle(GetRealNotificationsForStudentId query) {
        return realTimeNotificationRepository.findByStudentId(query.studentId());
    }

    @Override
    public List<RealTimeNotification> handle(GetRealNotificationsForTripId query) {
        return realTimeNotificationRepository.findByTripId(query.tripId());
    }

    @Override
    public List<RealTimeNotification> handle(GetRealNotificationsForUserAndTrip query) {
        return realTimeNotificationRepository.findByUserIdAndTripId(
                query.userId(),
                query.tripId()
        );
    }
}
