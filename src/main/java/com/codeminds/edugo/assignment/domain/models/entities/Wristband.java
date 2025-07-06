package com.codeminds.edugo.assignment.domain.models.entities;

import com.codeminds.edugo.assignment.domain.models.aggregates.Student;
import com.codeminds.edugo.shared.domain.models.entities.AuditableModel;
import com.codeminds.edugo.assignment.domain.models.valueobjects.WristbandStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "wristbands")
@NoArgsConstructor
@AllArgsConstructor
public class Wristband extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rfid_code", unique = true, nullable = false)
    private String rfidCode;

    @Column(name = "wristband_status")
    @Enumerated(EnumType.STRING)
    private WristbandStatus wristbandStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "wristband", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SensorScan> sensorScanList = new ArrayList<>();

    public Wristband(String rfidCode, WristbandStatus wristbandStatus, Student student) {
        this.rfidCode = rfidCode;
        this.wristbandStatus = wristbandStatus;
        this.student = student;
    }
}
