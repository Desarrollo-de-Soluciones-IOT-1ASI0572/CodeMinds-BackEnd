package com.codeminds.edugo.assignment.application.internal.commandservices.aggregates;

import com.codeminds.edugo.assignment.application.internal.outboundservices.acl.ExternalProfileService;
import com.codeminds.edugo.assignment.domain.models.aggregates.Student;
import com.codeminds.edugo.assignment.domain.models.commands.aggregates.CreateStudentCommand;
import com.codeminds.edugo.assignment.domain.models.commands.aggregates.DeleteStudentCommand;
import com.codeminds.edugo.assignment.domain.services.aggregates.student.StudentCommandService;
import com.codeminds.edugo.assignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;

    private final ProfileRepository profileRepository;

    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ProfileRepository profileRepository, ExternalProfileService  externalProfileService) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
        this.externalProfileService = externalProfileService;
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
        Profile parentProfile = profileRepository.findById(command.parentProfileId())
                .orElseThrow(() -> new RuntimeException("Parent profile not found"));

        if (!"ROLE_PARENT".equals(parentProfile.getRole())) {
            throw new RuntimeException("Profile is not a parent");
        }

        Profile driverProfile = profileRepository.findById(command.driverId())
                .orElseThrow(() -> new RuntimeException("Driver profile not found"));

        if (!"ROLE_DRIVER".equals(driverProfile.getRole())) {
            throw new RuntimeException("Profile is not a driver");
        }

        var student = new Student(
                command.name(),
                command.lastName(),
                command.homeAddress(),
                command.schoolAddress(),
                command.studentPhotoUrl(),
                parentProfile,
                command.driverId()
        );

        return Optional.of(studentRepository.save(student));
    }

    @Override
    public void handle(DeleteStudentCommand command) {
        studentRepository.deleteById(command.studentId());
    }
}
