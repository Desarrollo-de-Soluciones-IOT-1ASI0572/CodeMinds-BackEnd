package com.codeminds.edugo.identityassignment.infrastructure.api.rest.controllers;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.queries.aggregates.student.GetAllStudentsQuery;
import com.codeminds.edugo.identityassignment.domain.models.queries.aggregates.student.GetStudentsByIdQuery;
import com.codeminds.edugo.identityassignment.domain.services.aggregates.student.StudentQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentQueryService studentQueryService;

    public StudentController(StudentQueryService studentQueryService) {
        this.studentQueryService = studentQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentQueryService.handle(new GetAllStudentsQuery()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentQueryService.handle(new GetStudentsByIdQuery(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 