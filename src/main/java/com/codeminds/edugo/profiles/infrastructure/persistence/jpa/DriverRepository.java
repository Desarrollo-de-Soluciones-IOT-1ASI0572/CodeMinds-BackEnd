package com.codeminds.edugo.profiles.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeminds.edugo.profiles.domain.model.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUser_Id(Long userId);
}
