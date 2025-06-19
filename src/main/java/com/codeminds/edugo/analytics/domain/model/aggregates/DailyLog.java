package com.codeminds.edugo.analytics.domain.model.aggregates;

import com.codeminds.edugo.analytics.domain.model.commands.CreateDailyLogCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long driverUserId;
    private LocalDate date;
    private LocalTime arrivalTimeAtSchool;
    private LocalTime returnTimeHome;
    private double speed;

    @OneToOne(mappedBy = "dailyLog", cascade = CascadeType.ALL)
    private Incident incident;


    public DailyLog(CreateDailyLogCommand command) {
        this.driverUserId = command.driverUserId();
        this.date = command.date();
        this.arrivalTimeAtSchool = command.arrivalTimeAtSchool();
        this.returnTimeHome = command.returnTimeHome();
        this.speed = command.speed();

        Incident incident = new Incident();
        incident.setDetour(command.detour());
        incident.setLateness(command.lateness());
        incident.setSpeeding(command.speeding());
        incident.setDailyLog(this);

        this.incident = incident;
    }
}
