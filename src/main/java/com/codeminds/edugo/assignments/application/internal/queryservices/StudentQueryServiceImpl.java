package com.codeminds.edugo.assignments.application.internal.queryservices;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllStudentsQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByDriverProfileIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByParentProfileIdQuery;
import com.codeminds.edugo.assignments.domain.services.StudentQueryService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
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

    @Override
    public List<Student> handle(GetStudentsByParentProfileIdQuery query) {
        return studentRepository.findByParentProfileId(query.parentProfileId());
    }
}
