package com.codeminds.edugo.assignments.application.internal.commandservices;

import java.util.Optional;

import com.codeminds.edugo.assignments.domain.models.valueobjects.WristbandStatus;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.StudentRepository;
import org.springframework.stereotype.Service;

import com.codeminds.edugo.assignments.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.assignments.domain.models.commands.entities.DeleteWristbandCommand;
import com.codeminds.edugo.assignments.domain.models.entities.Wristband;
import com.codeminds.edugo.assignments.domain.services.WristbandCommandService;
import com.codeminds.edugo.assignments.infrastructure.persistence.jpa.WristbandRepository;


@Service
public class WristbandCommandServiceImpl implements WristbandCommandService {

    private final WristbandRepository wristbandRepository;

    private final StudentRepository studentRepository;

    public WristbandCommandServiceImpl(WristbandRepository wristbandRepository, StudentRepository studentRepository) {
        this.wristbandRepository = wristbandRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Wristband> handle(CreateWristbandCommand command) {
        return studentRepository.findById(command.studentId())
                .map(student -> {
                    var wristband = new Wristband(
                            command.rfidCode(),
                            WristbandStatus.valueOf(command.wristbandStatus().toUpperCase()),
                            student
                    );
                    return wristbandRepository.save(wristband);
                });
    }


    @Override
    public void handle(DeleteWristbandCommand command) {
        wristbandRepository.deleteById(command.wristbandId());
    }
}
