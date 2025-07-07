package com.codeminds.edugo.assignments.domain.services;

import com.codeminds.edugo.assignments.domain.models.commands.CreateStudentCommand;
import com.codeminds.edugo.assignments.domain.models.commands.DeleteStudentCommand;
import com.codeminds.edugo.assignments.domain.models.aggregates.Student;

import java.util.Optional;

public interface StudentCommandService {
    Optional<Student> handle(CreateStudentCommand command);
    void handle(DeleteStudentCommand command);
}
