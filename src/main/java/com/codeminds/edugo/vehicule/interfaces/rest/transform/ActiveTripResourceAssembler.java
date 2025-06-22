package com.codeminds.edugo.vehicule.interfaces.rest.transform;

import com.codeminds.edugo.identityassignment.interfaces.rest.resources.aggregates.student.StudentDto;
import com.codeminds.edugo.vehicule.domain.model.entities.Trip;
import com.codeminds.edugo.vehicule.interfaces.rest.resources.ActiveTripResource;

public class ActiveTripResourceAssembler {
    public static ActiveTripResource toResourceFromEntity(Trip trip) {
        return new ActiveTripResource(
                trip.getId(),
                trip.getStartTime(),
                trip.getStudents().stream()
                        .map(ts -> new StudentDto(
                                ts.getStudent().getId(),
                                ts.getStudent().getName(),
                                ts.getStudent().getLastName(),
                                ts.getStudent().getHomeAddress(),
                                ts.getStudent().getSchoolAddress(),
                                ts.getStudent().getStudentPhotoUrl()
                        ))
                        .toList()
        );
    }
}