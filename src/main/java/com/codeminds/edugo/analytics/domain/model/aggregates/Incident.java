package com.codeminds.edugo.analytics.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Setter
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean detour;
    private boolean lateness;
    private boolean speeding;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "daily_log_id")
    private DailyLog dailyLog;


    public boolean isDetour() {
        return detour;
    }

    public boolean isLateness() {
        return lateness;
    }

    public boolean isSpeeding() {
        return speeding;
    }

}
