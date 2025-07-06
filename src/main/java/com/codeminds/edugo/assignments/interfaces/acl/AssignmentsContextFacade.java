package com.codeminds.edugo.assignments.interfaces.acl;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.domain.models.queries.aggregates.student.GetStudentsByIdQuery;
import com.codeminds.edugo.assignments.domain.services.StudentQueryService;
import org.springframework.stereotype.Service;
@Service
public class AssignmentsContextFacade {
    private final StudentQueryService studentQueryService;

    public AssignmentsContextFacade(StudentQueryService studentQueryService) {
        this.studentQueryService = studentQueryService;
    }
    public Student getStudentById(Long studentId) {
        var query = new GetStudentsByIdQuery(studentId);
        return studentQueryService.handle(query)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
    }

    public boolean existsStudent(Long studentId) {
        var query = new GetStudentsByIdQuery(studentId);
        return studentQueryService.handle(query).isPresent();
    }

    public String getStudentFullName(Long studentId) {
        Student student = getStudentById(studentId);
        return student.getName() + " " + student.getLastName();
    }
}