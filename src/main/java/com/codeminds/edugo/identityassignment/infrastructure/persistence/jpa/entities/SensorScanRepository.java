package com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities;

import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorScanRepository extends JpaRepository<SensorScan,Long> {
    List<SensorScan> findAll();
    Optional<SensorScan> findById(Long id);
    Optional<SensorScan> findByWristbandId(Long wristbandId);
    List<SensorScan> findByScanType(ScanType scanType);
}
