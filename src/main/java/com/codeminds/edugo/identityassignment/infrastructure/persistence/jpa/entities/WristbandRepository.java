package com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WristbandRepository extends JpaRepository<Wristband, Long> {
    List<Wristband> findAll();

    Optional<Wristband> findById(Long id);

    Optional<Wristband> findByStudentId(Long studentId);

    List<Wristband> findByWristbandStatus(WristbandStatus wristbandStatus);

    Optional<Wristband> findByRfidCode(String rfidCode);
}
