package com.codeminds.edugo.profiles.application.internal.commandservices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.exceptions.DriverNotFoundException;
import com.codeminds.edugo.profiles.domain.exceptions.SameUserException;
import com.codeminds.edugo.profiles.domain.model.commands.CreateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.UpdateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.entities.Driver;
import com.codeminds.edugo.profiles.domain.services.DriverCommandService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.DriverRepository;

@Service
public class DriverCommandServiceImpl implements DriverCommandService {

    private final DriverRepository driverRepository;

    public DriverCommandServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Long handle(CreateDriverCommand command, User user) {
        var sameUser = driverRepository.findByUser_Id(user.getId());
        if (sameUser.isPresent()) {
            throw new SameUserException(command.userId());
        }
        Driver driver = new Driver(command, user);
        driverRepository.save(driver);
        return driver.getId();
    }

    @Override
    public Optional<Driver> handle(UpdateDriverCommand command) {
        var driver = driverRepository.findById(command.id());
        if (driver.isEmpty()) {
            return Optional.empty();
        }
        var driverToUpdate = driver.get();
        Driver updatedDriver = driverRepository.save(driverToUpdate.update(command));
        return Optional.of(updatedDriver);
    }

    @Override
    public void handle(DeleteDriverCommand command) {
        var driver = driverRepository.findById(command.id());
        if (driver.isEmpty()) {
            throw new DriverNotFoundException(command.id());
        }
        driverRepository.delete(driver.get());
    }

}
