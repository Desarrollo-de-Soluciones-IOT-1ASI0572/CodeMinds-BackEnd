package com.codeminds.edugo.profiles.domain.model.aggregates;

import com.codeminds.edugo.profiles.domain.model.commands.CreateProfileCommand;
import com.codeminds.edugo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @NotNull(message = "User ID must not be null")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @NotNull(message = "Name must not be null")
    @Column(name = "name", nullable = false)
    private String fullName;

    @NotNull(message = "Email must not be null")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Mobile number must not be null")
    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    @NotNull(message = "Address must not be null")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Gender must not be null")
    @Column
    private String gender;

    @NotNull(message = "Photo URL must not be null")
    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @NotNull(message = "Role must not be null")
    @Column(name = "role", nullable = false)
    private String role;

    public Profile(String fullName, String email, String mobileNumber, String address, String gender, String photoUrl) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.gender = gender;
        this.photoUrl = photoUrl;
    }

    public Profile(CreateProfileCommand command, Long userId, String role) {
        this.fullName = command.fullName();
        this.email = command.email();
        this.mobileNumber = command.mobileNumber();
        this.address = command.address();
        this.gender = command.gender();
        this.photoUrl = command.photoUrl();
        this.userId = userId;
        this.role = role;
    }

    public Profile() {
    }

    public void updateName(String fullName) {
        this.fullName = fullName;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void updatePhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
