package com.codeminds.edugo.assignment.domain.services.aggregates.student;

import com.codeminds.edugo.assignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.assignment.domain.models.commands.aggregates.DeleteStudentCommand;
import com.codeminds.edugo.assignment.domain.models.aggregates.Student;

import java.util.Optional;

public interface StudentCommandService {
    Optional<Student> handle(CreateStudentCommand command);
    void handle(DeleteStudentCommand command);
}
