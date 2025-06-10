package com.codeminds.edugo.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.exceptions.ParentNotFoundException;
import com.codeminds.edugo.profiles.domain.exceptions.SameUserException;
import com.codeminds.edugo.profiles.domain.model.commands.CreateParentCommand;
import com.codeminds.edugo.profiles.domain.model.commands.DeleteParentCommand;
import com.codeminds.edugo.profiles.domain.model.entities.Parent;
import com.codeminds.edugo.profiles.domain.services.ParentCommandService;
import com.codeminds.edugo.profiles.infrastructure.persistence.jpa.ParentRepository;

@Service
public class ParentCommandServiceImpl implements ParentCommandService {

    private final ParentRepository parentRepository;

    public ParentCommandServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Long handle(CreateParentCommand command, User user) {
        var sameUser = parentRepository.findByUser_Id(user.getId());
        if (sameUser.isPresent()) {
            throw new SameUserException(command.userId());
        }
        Parent parent = new Parent(command, user);
        parentRepository.save(parent);
        return parent.getId();
    }

    @Override
    public void handle(DeleteParentCommand command) {
        var parent = parentRepository.findById(command.id());
        if (parent.isEmpty()) {
            throw new ParentNotFoundException(command.id());
        }
        parentRepository.delete(parent.get());
    }

}
