package com.codeminds.edugo.tracking.application.internal.outboundservices;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.interfaces.acl.AssignmentsContextFacade;
import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class ExternalAssignmentsService {
    private final AssignmentsContextFacade assignmentsContextFacade;

    public ExternalAssignmentsService(AssignmentsContextFacade assignmentsContextFacade) {
        this.assignmentsContextFacade = assignmentsContextFacade;
    }

    public String getStudentFullName(Long studentId) {
        Student student = assignmentsContextFacade.getStudentById(studentId);
        return student.getName() + " " + student.getLastName();
    }

    public boolean existsStudent(Long studentId) {
        return assignmentsContextFacade.existsStudent(studentId);
    }

    public StudentDto getStudentById(Long studentId) {
        Student student = assignmentsContextFacade.getStudentById(studentId);
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getHomeAddress(),
                student.getSchoolAddress(),
                student.getStudentPhotoUrl()
                //student.getParentProfileId()
        );
    }
}