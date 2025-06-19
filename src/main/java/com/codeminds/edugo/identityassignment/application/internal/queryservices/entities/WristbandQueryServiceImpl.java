package com.codeminds.edugo.identityassignment.application.internal.queryservices.entities;

import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.wristband.GetAllWristbandsQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.wristband.GetWristbandsByIdQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.wristband.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.entities.wristband.GetWristbandsByWristbandStatusQuery;
import com.codeminds.edugo.identityassignment.domain.services.entities.wristband.WristbandQueryService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WristbandQueryServiceImpl implements WristbandQueryService {
    private final WristbandRepository wristbandRepository;

    public WristbandQueryServiceImpl(WristbandRepository wristbandRepository) {
        this.wristbandRepository = wristbandRepository;
    }

    public List<Wristband> handle(GetAllWristbandsQuery query) {
        return wristbandRepository.findAll();
    }

    public List<Wristband> handle(GetWristbandsByIdQuery query) {
        return wristbandRepository.findById(query.wristbandId())
                .map(List::of)
                .orElse(List.of());
    }

    public List<Wristband> handle(GetWristbandsByStudentIdQuery query) {
        return wristbandRepository.findByStudentId(query.studentId())
                .map(List::of)
                .orElse(List.of());
    }

    public List<Wristband> handle(GetWristbandsByWristbandStatusQuery query) {
        return wristbandRepository.findByWristbandStatus(query.wristbandStatus());
    }
}
