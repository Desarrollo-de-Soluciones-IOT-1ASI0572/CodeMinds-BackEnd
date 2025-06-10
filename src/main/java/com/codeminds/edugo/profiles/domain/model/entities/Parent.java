package com.codeminds.edugo.profiles.domain.model.entities;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.commands.CreateParentCommand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Parent() {
    }

    public Parent(CreateParentCommand command, User user) {
        this.user = user;
    }

    public Parent(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return this.user.getId();
    }
}
