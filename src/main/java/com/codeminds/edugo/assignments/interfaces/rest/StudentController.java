package com.codeminds.edugo.assignments.interfaces.rest;

import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.domain.models.commands.DeleteStudentCommand;
import com.codeminds.edugo.assignments.domain.models.queries.GetAllStudentsQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByDriverProfileIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByIdQuery;
import com.codeminds.edugo.assignments.domain.models.queries.GetStudentsByParentProfileIdQuery;
import com.codeminds.edugo.assignments.domain.services.StudentCommandService;
import com.codeminds.edugo.assignments.domain.services.StudentQueryService;
import com.codeminds.edugo.assignments.interfaces.rest.resources.CreateStudentResource;
import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentResource;
import com.codeminds.edugo.assignments.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import com.codeminds.edugo.assignments.interfaces.rest.transform.StudentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student", description = "Students Endpoint")
public class StudentController {
    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    @PostMapping
    public ResponseEntity<List<StudentResource>> CreateStudent(
            @RequestBody CreateStudentResource resource){
        Optional<Student> student = studentCommandService
                .handle(CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource));

        return student.map(s ->
                        new ResponseEntity<>(
                                List.of(StudentResourceFromEntityAssembler.toResourceFromEntity(s)),
                                CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentResource>> getAllStudents() {
        var getAllStudents = new GetAllStudentsQuery();
        var student = studentQueryService.handle(getAllStudents);
        var studentResources = student.stream().map(
                StudentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(studentResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResource> getStudentById(@PathVariable Long id) {
        return studentQueryService.handle(new GetStudentsByIdQuery(id))
                .map(StudentResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentCommandService.handle(new DeleteStudentCommand(id));
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/driver/{driverProfileId}")
    public ResponseEntity<List<StudentResource>> getStudentsByDriverProfileId(
            @PathVariable Long driverProfileId) {

        List<Student> students = studentQueryService.handle(
                new GetStudentsByDriverProfileIdQuery(driverProfileId)
        );

        List<StudentResource> resources = students.stream()
                .map(StudentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/parent/{parentProfileId}")
    public ResponseEntity<List<StudentResource>> getStudentsByParentProfileId(
            @PathVariable Long parentProfileId) {

        List<Student> students = studentQueryService.handle(
                new GetStudentsByParentProfileIdQuery(parentProfileId)
        );

        List<StudentResource> resources = students.stream()
                .map(StudentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }
}
