package com.codeminds.edugo.profiles.domain.model.aggregates;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.profiles.domain.model.commands.UpdateProfileCommand;
import com.codeminds.edugo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @NotNull(message = "Profile full name cannot be null")
    private String full_name;
    @NotNull(message = "Profile email cannot be null")
    private String email;
    @NotNull(message = "Profile phone number cannot be null")
    private String phone_number;
    @NotNull(message = "Gender cannot be null")
    private String gender;
    @NotNull(message = "Profile picture cannot be null")
    private String profile_picture_url;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Profile() {
    }

    public Profile(CreateProfileCommand command, User user) {
        this.full_name = command.full_name();
        this.email = command.email();
        this.phone_number = command.phone_number();
        this.gender = command.gender();
        this.profile_picture_url = command.profile_picture_url();
        this.user = user;
    }

    public Profile update(UpdateProfileCommand command) {
        this.full_name = command.full_name();
        this.email = command.email();
        this.phone_number = command.phone_number();
        this.profile_picture_url = command.profile_picture_url();
        return this;
    }

    public Long getUserId() {
        return this.user.getId();
    }
}
