package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentResource;
import com.codeminds.edugo.assignments.interfaces.rest.resources.WristbandResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity) {
        WristbandResource wristbandResource = null;


        return new StudentResource(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getHomeAddress(),
                entity.getSchoolAddress(),
                entity.getStudentPhotoUrl(),
                entity.getWristbandId(),
                entity.getParentProfileId(),
                entity.getDriverId()
        );
    }
}
