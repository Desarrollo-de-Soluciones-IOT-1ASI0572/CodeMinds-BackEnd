package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

import java.util.Optional;

import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.aggregates.StudentRepository;
import org.springframework.stereotype.Service;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteWristbandCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.services.entities.wristband.WristbandCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;


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
