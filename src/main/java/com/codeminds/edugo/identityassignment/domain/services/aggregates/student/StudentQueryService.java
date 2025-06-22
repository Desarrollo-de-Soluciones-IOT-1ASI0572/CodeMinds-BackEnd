package com.codeminds.edugo.identityassignment.domain.services.aggregates.student;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;

import com.codeminds.edugo.identityassignment.domain.models.queries.aggregates.student.GetAllStudentsQuery;

import com.codeminds.edugo.identityassignment.domain.models.queries.aggregates.student.GetStudentsByDriverProfileIdQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.aggregates.student.GetStudentsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
    List<Student> handle (GetAllStudentsQuery query);
    Optional<Student> handle (GetStudentsByIdQuery query);
    List<Student> handle(GetStudentsByDriverProfileIdQuery query);
}
