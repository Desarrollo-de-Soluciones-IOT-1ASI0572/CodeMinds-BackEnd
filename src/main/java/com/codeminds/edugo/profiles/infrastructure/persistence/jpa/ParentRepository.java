package com.codeminds.edugo.profiles.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeminds.edugo.profiles.domain.model.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUser_Id(Long userId);
}
