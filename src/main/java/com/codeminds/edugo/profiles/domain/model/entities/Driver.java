package com.codeminds.edugo.profiles.domain.model.entities;

import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.commands.CreateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.UpdateDriverCommand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotNull(message = "DNI cannot be null")
    private String dni;
    @NotNull(message = "Plate number cannot be null")
    private String plateNumber;
    @NotNull(message = "Vehicle brand cannot be null")
    private String vehicleBrand;
    @NotNull(message = "Vehicle model cannot be null")
    private String vehicleModel;
    @NotNull(message = "License number cannot be null")
    private String licenseNumber;
    @NotNull(message = "Insurance number cannot be null")
    private String insuranceNumber;

    public Driver() {
    }

    public Driver(CreateDriverCommand command, User user) {
        this.dni = "";
        this.plateNumber = "";
        this.vehicleBrand = "";
        this.vehicleModel = "";
        this.licenseNumber = "";
        this.insuranceNumber = "";
        this.user = user;
    }

    public Driver(User user) {
        this.dni = "";
        this.plateNumber = "";
        this.vehicleBrand = "";
        this.vehicleModel = "";
        this.licenseNumber = "";
        this.insuranceNumber = "";
        this.user = user;
    }

    public Driver update(UpdateDriverCommand command) {
        this.dni = command.dni();
        this.plateNumber = command.plateNumber();
        this.vehicleBrand = command.vehicleBrand();
        this.vehicleModel = command.vehicleModel();
        this.licenseNumber = command.licenseNumber();
        this.insuranceNumber = command.insuranceNumber();
        return this;
    }

    public Long getUserId() {
        return this.user.getId();
    }
}
