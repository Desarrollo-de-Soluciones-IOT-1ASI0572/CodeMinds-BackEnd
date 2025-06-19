package com.codeminds.edugo.identityassignment.application.internal.commandservices.entities;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.identityassignment.domain.models.commands.entities.CreateWristbandCommand;
import com.codeminds.edugo.identityassignment.domain.models.commands.entities.DeleteWristbandCommand;
import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.identityassignment.domain.services.entities.wristband.WristbandCommandService;
import com.codeminds.edugo.identityassignment.infrastructure.persistence.jpa.entities.WristbandRepository;


@Service
public class WristbandCommandServiceImpl implements WristbandCommandService {

    private final WristbandRepository wristbandRepository;

    public WristbandCommandServiceImpl(WristbandRepository wristbandRepository) {
        this.wristbandRepository = wristbandRepository;
    }

    @Override
    public Optional<Wristband> handle(CreateWristbandCommand command) {
        var wristband = new Wristband(
            command.rfidCode(),
            command.wristbandStatus(),
            command.student());
        var createdWristband = wristbandRepository.save(wristband);
        return Optional.of(createdWristband);
    }

    @Override
    public void handle(DeleteWristbandCommand command) {
        wristbandRepository.deleteById(command.wristbandId());
    }
}
