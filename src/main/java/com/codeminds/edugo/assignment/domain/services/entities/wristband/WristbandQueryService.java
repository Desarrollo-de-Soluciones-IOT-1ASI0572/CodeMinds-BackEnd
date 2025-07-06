package com.codeminds.edugo.assignment.domain.services.entities.wristband;

import com.codeminds.edugo.assignment.domain.models.entities.Wristband;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetAllWristbandsQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByStudentIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.entities.wristband.GetWristbandsByWristbandStatusQuery;

import java.util.List;

public interface WristbandQueryService {
    List<Wristband> handle(GetAllWristbandsQuery query);
    List<Wristband> handle(GetWristbandsByIdQuery query);
    List<Wristband> handle(GetWristbandsByStudentIdQuery query);
    List<Wristband> handle(GetWristbandsByWristbandStatusQuery query);
}
