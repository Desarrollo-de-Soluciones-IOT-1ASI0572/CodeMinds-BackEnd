package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.entities.Wristband;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllWristbandsQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetWristbandsByWristbandStatusQuery;

import java.util.List;

public interface WristbandQueryService {
    List<Wristband> handle(GetAllWristbandsQuery query);
    List<Wristband> handle(GetWristbandsByIdQuery query);
    List<Wristband> handle(GetWristbandsByStudentIdQuery query);
    List<Wristband> handle(GetWristbandsByWristbandStatusQuery query);
}
