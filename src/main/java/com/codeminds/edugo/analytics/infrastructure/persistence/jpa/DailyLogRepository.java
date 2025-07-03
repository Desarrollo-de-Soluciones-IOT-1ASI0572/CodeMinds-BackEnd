package com.codeminds.edugo.analytics.infrastructure.persistence.jpa;

import com.codeminds.edugo.analytics.domain.model.aggregates.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
    List<DailyLog> findByDriverUserId(Long driverUserId);
//    List<DailyLog> findAllByDriverId(Long driverUserId);
}
