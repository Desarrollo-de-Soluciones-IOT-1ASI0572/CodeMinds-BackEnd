package com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.aggregates;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    List<Student> findByDriverId(Long driverId);
    List<Student> findByParentProfileId(Long parentProfileId);
}
