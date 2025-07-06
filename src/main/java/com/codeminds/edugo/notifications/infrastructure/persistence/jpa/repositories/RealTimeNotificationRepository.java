package com.codeminds.edugo.notifications.infrastructure.persistence.jpa.repositories;

import com.codeminds.edugo.notifications.domain.model.aggregates.RealTimeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealTimeNotificationRepository extends JpaRepository<RealTimeNotification, Long> {
    List<RealTimeNotification> findAll();

    List<RealTimeNotification> findByUserType(String userType);

    List<RealTimeNotification> findByUserId(Long userType);
    List<RealTimeNotification> findByStudentId(Long studentId);
    List<RealTimeNotification> findByTripId(Long tripId);
    List<RealTimeNotification> findByUserIdAndTripId(Long userId, Long tripId);

}
