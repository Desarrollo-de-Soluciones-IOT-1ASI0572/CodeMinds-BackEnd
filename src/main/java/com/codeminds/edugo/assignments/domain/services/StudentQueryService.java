package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;

import com.codeminds.edugo.assignments.domain.models.queries.GetAllStudentsQuery;

import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByDriverProfileIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByParentProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
    List<Student> handle (GetAllStudentsQuery query);
    Optional<Student> handle (GetStudentsByIdQuery query);
    List<Student> handle(GetStudentsByDriverProfileIdQuery query);
    List<Student> handle(GetStudentsByParentProfileIdQuery query);
}
