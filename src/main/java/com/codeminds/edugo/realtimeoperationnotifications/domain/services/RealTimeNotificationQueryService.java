package com.codeminds.edugo.realtimeoperationnotifications.domain.services;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.queries.GetAllRealTimeNotificationsQuery;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.queries.GetRealNotificationsForUserId;
import com.codeminds.edugo.realtimeoperationnotifications.domain.model.queries.GetRealNotificationsForUserType;

import java.util.List;

public interface RealTimeNotificationQueryService {
    List<RealTimeNotification> handle(GetAllRealTimeNotificationsQuery query);

    List<RealTimeNotification> handle(GetRealNotificationsForUserType query);

    List<RealTimeNotification> handle(GetRealNotificationsForUserId query);
}
