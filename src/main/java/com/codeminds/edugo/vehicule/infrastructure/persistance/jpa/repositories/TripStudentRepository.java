package com.codeminds.edugo.vehicule.infrastructure.persistance.jpa.repositories;

import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripStudentRepository extends JpaRepository<TripStudent, Long> {

    List<TripStudent> findByTrip_Id(Long tripId);

    TripStudent findByTrip_IdAndStudentId(Long tripId, Long studentId);
}
