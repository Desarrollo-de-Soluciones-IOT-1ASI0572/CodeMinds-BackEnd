package com.codeminds.edugo.assignments.application.internal.commandservices;

import com.codeminds.edugo.assignments.application.internal.outboundservices.acl.ExternalProfileService;
import com.codeminds.edugo.assignments.domain.models.aggregates.Student;
import com.codeminds.edugo.assignments.domain.models.commands.CreateStudentCommand;
import com.codeminds.edugo.assignments.domain.models.commands.DeleteStudentCommand;
import com.codeminds.edugo.assignments.domain.services.StudentCommandService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;

    private final ProfileRepository profileRepository;

    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ProfileRepository profileRepository, ProfileRepository profileRepository1, ExternalProfileService  externalProfileService) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository1;
        this.externalProfileService = externalProfileService;
    }
    @Override
    public Optional<Student> handle(CreateStudentCommand command) {

        /*Profile parentProfile = profileRepository.findById(command.parentProfileId())
                .orElseThrow(() -> new RuntimeException("Parent profile not found"));

        if (!"ROLE_PARENT".equals(parentProfile.getRole())) {
            throw new RuntimeException("Profile is not a parent");
        }

        Profile driverProfile = profileRepository.findById(command.driverId())
                .orElseThrow(() -> new RuntimeException("Driver profile not found"));

        if (!"ROLE_DRIVER".equals(driverProfile.getRole())) {
            throw new RuntimeException("Profile is not a driver");
        }*/
        externalProfileService.validateParentProfile(command.parentProfileId());
        externalProfileService.validateDriverProfile(command.driverId());

        var student = new Student(
                command.name(),
                command.lastName(),
                command.homeAddress(),
                command.schoolAddress(),
                command.studentPhotoUrl(),
                command.parentProfileId(), // Solo el ID
                command.driverId()
        );

        return Optional.of(studentRepository.save(student));
    }

    @Override
    public void handle(DeleteStudentCommand command) {
        studentRepository.deleteById(command.studentId());
    }
}
