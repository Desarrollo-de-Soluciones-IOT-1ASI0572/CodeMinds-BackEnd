package com.codeminds.edugo.identityassignment.application.internal.commandservices.aggregates;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.aggregates.DeleteStudentCommand;
import com.codeminds.edugo.identityassignment.domain.services.aggregates.student.StudentCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;

    private final ProfileRepository profileRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
    }

    /*@Override
    public Optional<Student> handle(CreateStudentCommand command){
        var student = new Student(
                command.name(),
                command.lastName(),
                command.homeAddress(),
                command.schoolAddress(),
                command.studentPhotoUrl());

        var createdStudent = studentRepository.save(student);

        return Optional.of(createdStudent);
    }*/
    @Override
    public Optional<Student> handle(CreateStudentCommand command) {
        // Busca el perfil del padre
        Profile parentProfile = profileRepository.findById(command.parentProfileId())
                .orElseThrow(() -> new RuntimeException("Parent profile not found with id: " + command.parentProfileId()));

        // Validar que el perfil tenga rol de padre
        if (!"ROLE_PARENT".equals(parentProfile.getRole())) {
            throw new RuntimeException("Profile with id " + command.parentProfileId() + " is not a parent");
        }

        // Crear el estudiante con el parent profile
        var student = new Student(
                command.name(),
                command.lastName(),
                command.homeAddress(),
                command.schoolAddress(),
                command.studentPhotoUrl(),
                parentProfile);  // Asignar el parent profile

        // Guardar y retornar el estudiante
        var createdStudent = studentRepository.save(student);
        return Optional.of(createdStudent);
    }

    @Override
    public void handle(DeleteStudentCommand command) {
        studentRepository.deleteById(command.studentId());
    }
}
