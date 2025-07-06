package com.codeminds.edugo.assignment.interfaces.rest.resources.entities.wristband;

import com.codeminds.edugo.assignment.domain.models.valueobjects.WristbandStatus;
import com.codeminds.edugo.assignment.interfaces.rest.resources.aggregates.student.StudentDto;
import com.codeminds.edugo.assignment.interfaces.rest.resources.entities.sensorscan.SensorScanResource;

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

