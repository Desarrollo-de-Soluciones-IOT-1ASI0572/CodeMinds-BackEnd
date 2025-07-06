package com.codeminds.edugo.assignments.interfaces.rest.resources;

import com.codeminds.edugo.assignments.domain.models.valueobjects.WristbandStatus;

import java.util.List;

/*public record WristbandResource(
        Long id,
        String rfidCode,
        WristbandStatus wristbandStatus,
        Student student,
        List<SensorScan> sensorScanList
) {}*/

public record WristbandResource(
        Long id,
        String rfidCode,
        WristbandStatus wristbandStatus,
        StudentDto student,
        List<SensorScanResource> sensorScans
) {}

