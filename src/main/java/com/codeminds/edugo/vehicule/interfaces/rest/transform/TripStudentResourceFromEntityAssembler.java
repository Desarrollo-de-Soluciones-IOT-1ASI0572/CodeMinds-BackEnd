package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.TripStudentResource;

public class TripStudentResourceFromEntityAssembler {
    public static TripStudentResource toResourceFromEntity(TripStudent ts) {
        return new TripStudentResource(
                ts.getId(),
                ts.getStudentId(),
                ts.isAttended(),
                ts.getBoardedAt(),
                ts.getExitedAt()
        );
    }
}