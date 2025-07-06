package com.codeminds.edugo.tracking.interfaces.rest.transform;

import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentDto;
import com.codeminds.edugo.tracking.domain.model.entities.TripStudent;
import com.codeminds.edugo.tracking.interfaces.rest.resources.TripStudentResource;

public class TripStudentResourceFromEntityAssembler {
    public static TripStudentResource toResourceFromEntity(TripStudent ts) {
        var s = ts.getStudent();

        StudentDto studentResource = new StudentDto(
                s.getId(),
                s.getName(),
                s.getLastName(),
                s.getHomeAddress(),
                s.getSchoolAddress(),
                s.getStudentPhotoUrl()
        );

        return new TripStudentResource(
                ts.getId(),
                studentResource,
                ts.isAttended(),
                ts.getBoardedAt(),
                ts.getExitedAt()
        );
    }
}
