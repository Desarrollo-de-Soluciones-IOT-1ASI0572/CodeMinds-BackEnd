package com.codeminds.edugo.assignments.application.internal.queryservices;

import com.codeminds.edugo.assignments.domain.models.entities.Wristband;
import com.codeminds.edugo.assignments.domain.models.queries.entities.wristband.GetAllWristbandsQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.wristband.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.wristband.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.entities.wristband.GetWristbandsByWristbandStatusQuery;
import com.codeminds.edugo.assignments.domain.services.WristbandQueryService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.WristbandRepository;
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
