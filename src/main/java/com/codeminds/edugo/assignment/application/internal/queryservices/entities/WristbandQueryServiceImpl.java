package com.codeminds.edugo.assignment.application.internal.queryservices.entities;

import com.codeminds.edugo.assignment.domain.models.entities.Wristband;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetAllWristbandsQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByWristbandStatusQuery;
import com.codeminds.edugo.assignment.domain.services.entities.wristband.WristbandQueryService;
import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.entities.WristbandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
