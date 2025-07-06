package com.codeminds.edugo.notifications.domain.services;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.notifications.domain.model.queries.*;

import java.util.List;

public interface RealTimeNotificationQueryService {

    List<RealTimeNotification> handle(GetAllRealTimeNotificationsQuery query);

    List<RealTimeNotification> handle(GetRealNotificationsForUserType query);

    List<RealTimeNotification> handle(GetRealNotificationsForUserId query);

    List<RealTimeNotification> handle(GetRealNotificationsForStudentId query);

    List<RealTimeNotification> handle(GetRealNotificationsForTripId query);

    List<RealTimeNotification> handle(GetRealNotificationsForUserAndTrip query);
}
