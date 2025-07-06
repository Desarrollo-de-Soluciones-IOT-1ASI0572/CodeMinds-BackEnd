package com.codeminds.edugo.assignment.application.internal.queryservices.aggregates;

import com.codeminds.edugo.assignment.domain.models.aggregates.Student;
import com.codeminds.edugo.assignment.domain.models.queries.aggregates.student.GetAllStudentsQuery;
import com.codeminds.edugo.assignment.domain.models.queries.aggregates.student.GetStudentsByDriverProfileIdQuery;
import com.codeminds.edugo.assignment.domain.models.queries.aggregates.student.GetStudentsByIdQuery;
import com.codeminds.edugo.assignment.domain.services.aggregates.student.StudentQueryService;
import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentQueryServiceImpl implements StudentQueryService {
    private final StudentRepository studentRepository;

    public StudentQueryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> handle(GetAllStudentsQuery query) {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> handle(GetStudentsByIdQuery query) {
        return studentRepository.findById(query.studentId());
    }

    @Override
    public List<Student> handle(GetStudentsByDriverProfileIdQuery query) {
        return studentRepository.findByDriverId(query.driverProfileId());
    }
}
