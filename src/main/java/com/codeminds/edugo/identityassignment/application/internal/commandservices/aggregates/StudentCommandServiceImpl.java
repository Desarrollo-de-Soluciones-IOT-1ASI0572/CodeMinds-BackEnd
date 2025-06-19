package com.codeminds.edugo.identityassignment.application.internal.commandservices.aggregates;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.aggregates.DeleteStudentCommand;
import com.codeminds.edugo.identityassignment.domain.services.aggregates.student.StudentCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> handle(CreateStudentCommand command){
        var student = new Student(
                command.name(),
                command.lastName(),
                command.homeAddress(),
                command.schoolAddress(),
                command.studentPhotoUrl());

        var createdStudent = studentRepository.save(student);

        return Optional.of(createdStudent);
    }

    @Override
    public void handle(DeleteStudentCommand command) {
        studentRepository.deleteById(command.studentId());
    }
}
