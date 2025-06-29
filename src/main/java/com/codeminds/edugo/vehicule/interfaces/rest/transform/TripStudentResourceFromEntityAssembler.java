package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentDto;
import com.codeminds.edugo.vehicule.domain.model.entities.TripStudent;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.TripStudentResource;

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
