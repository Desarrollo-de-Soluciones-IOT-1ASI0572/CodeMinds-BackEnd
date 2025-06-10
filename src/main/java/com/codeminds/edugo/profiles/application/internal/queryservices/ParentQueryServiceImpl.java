package com.codeminds.edugo.profiles.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.profiles.domain.model.entities.Parent;
import com.codeminds.edugo.profiles.domain.model.queries.GetAllParentsQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByUserIdQuery;
import com.codeminds.edugo.profiles.domain.services.ParentQueryService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.ParentRepository;

@Service
public class ParentQueryServiceImpl implements ParentQueryService {

    private final ParentRepository parentRepository;

    public ParentQueryServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Optional<Parent> handle(GetParentByIdQuery query) {
        return parentRepository.findById(query.id());
    }

    @Override
    public Optional<Parent> handle(GetParentByUserIdQuery query) {
        return parentRepository.findByUser_Id(query.user_id());
    }

    @Override
    public List<Parent> handle(GetAllParentsQuery query) {
        return parentRepository.findAll();
    }

}
