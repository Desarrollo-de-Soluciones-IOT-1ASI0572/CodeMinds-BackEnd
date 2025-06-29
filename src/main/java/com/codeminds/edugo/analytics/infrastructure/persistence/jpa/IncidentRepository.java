package com.codeminds.edugo.analytics.infrastructure.persistence.jpa;

import com.codeminds.edugo.analytics.domain.model.aggregates.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
