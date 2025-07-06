package com.codeminds.edugo.assignment.interfaces.rest.transform.aggregates.student;

import com.codeminds.edugo.assignment.domain.models.aggregates.Student;
import com.codeminds.edugo.assignment.interfaces.rest.resources.aggregates.student.StudentResource;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband.WristbandResource;
import com.codeminds.edugo.assignment.interfaces.rest.transform.entities.wristband.WristbandResourceFromEntityAssembler;
import com.codeminds.edugo.profiles.interfaces.rest.resources.ProfileResource;
import com.codeminds.edugo.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity) {
        WristbandResource wristbandResource = null;
        if (entity.getWristband() != null) {
            wristbandResource = WristbandResourceFromEntityAssembler.toResourceFromEntity(entity.getWristband());
        }

        ProfileResource profileResource = null;
        if (entity.getParentProfile() != null) {
            profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(entity.getParentProfile());
        }

        return new StudentResource(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getHomeAddress(),
                entity.getSchoolAddress(),
                entity.getStudentPhotoUrl(),
                wristbandResource,
                profileResource,
                entity.getDriverId()
        );
    }
}
