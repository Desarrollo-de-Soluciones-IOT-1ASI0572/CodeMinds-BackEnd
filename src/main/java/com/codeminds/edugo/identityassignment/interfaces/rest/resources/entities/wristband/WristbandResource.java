package com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.wristband;

import com.codeminds.edugo.identityassignment.domain.models.aggregates.Student;
import com.codeminds.edugo.identityassignment.domain.models.entities.SensorScan;
import com.codeminds.edugo.identityassignment.domain.models.valueobjects.WristbandStatus;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentDto;
import com.codeminds.edugo.identityassignment.interfaces.rest.resources.entities.sensorscan.SensorScanResource;

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

