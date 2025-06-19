package com.codeminds.edugo.realtimeoperationnotifications.infrastructure.persistence.jpa.repositories;

import com.codeminds.edugo.realtimeoperationnotifications.domain.model.aggregates.RealTimeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealTimeNotificationRepository extends JpaRepository<RealTimeNotification, Long> {
    List<RealTimeNotification> findAll();

    List<RealTimeNotification> findByUserType(String userType);

    List<RealTimeNotification> findByUserId(Long userType);
}
