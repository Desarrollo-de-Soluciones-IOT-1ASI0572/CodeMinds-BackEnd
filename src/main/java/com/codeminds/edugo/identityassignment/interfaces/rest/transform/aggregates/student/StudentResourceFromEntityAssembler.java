package com.codeminds.edugo.identityassignment.interfaces.rest.transform.aggregates.student;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentResource;

public class StudentResourceFromEntityAssembler {
    public static StudentResource toResourceFromEntity(Student entity){
        return new StudentResource(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getHomeAddress(),
                entity.getSchoolAddress(),
                entity.getStudentPhotoUrl(),
                entity.getWristband()
        );
    }
}
