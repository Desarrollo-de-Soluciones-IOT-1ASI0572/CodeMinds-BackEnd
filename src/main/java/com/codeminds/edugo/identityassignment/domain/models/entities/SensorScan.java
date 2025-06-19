package com.codeminds.edugo.identityassignment.domain.models.entities;

import com.codeminds.edugo.identityassignment.domain.models.valueobjects.ScanType;
import com.codeminds.edugo.shared.domain.models.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sensor_scans")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorScan extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scan_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ScanType scanType;

    @Column(name = "scan_time", nullable = false)
    private LocalDateTime scanTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wristband_id", nullable = false)
    private Wristband wristband;

    public SensorScan(ScanType scanType, Wristband wristband, LocalDateTime scanTime) {
        this.scanType = scanType;
        this.wristband = wristband;
        this.scanTime = scanTime;
    }
}
