package com.codeminds.edugo.assignments.interfaces.rest.transform;

import com.codeminds.edugo.assignments.domain.models.entities.Wristband;
import com.codeminds.edugo.assignments.interfaces.rest.resources.StudentDto;
import com.codeminds.edugo.assignments.interfaces.rest.resources.SensorScanResource;
import com.codeminds.edugo.assignments.interfaces.rest.resources.WristbandResource;

import java.util.List;

public class WristbandResourceFromEntityAssembler {
    public static WristbandResource toResourceFromEntity(Wristband entity){
        StudentDto studentDto = new StudentDto(
                entity.getStudent().getId(),
                entity.getStudent().getName(),
                entity.getStudent().getLastName(),
                entity.getStudent().getHomeAddress(),
                entity.getStudent().getSchoolAddress(),
                entity.getStudent().getStudentPhotoUrl()
        );

        List<SensorScanResource> sensorScanResources = entity.getSensorScanList()
                .stream()
                .map(scan -> new SensorScanResource(
                        scan.getId(),
                        scan.getScanTime(),
                        scan.getScanType(),
                        scan.getWristband().getId()
                ))
                .toList();


        return new WristbandResource(
                entity.getId(),
                entity.getRfidCode(),
                entity.getWristbandStatus(),
                entity.getStudent().getId()
        );

    }
}
