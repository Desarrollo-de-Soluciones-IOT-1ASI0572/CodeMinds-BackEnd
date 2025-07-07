package com.codeminds.edugo.tracking.interfaces.rest.resources;
import java.time.LocalDateTime;

public record VehicleLocationResource(Long vehicleId,
                                      Long tripId,
                                      GeoPoint location,
                                      LocalDateTime lastUpdate,
                                      Double speed) {
}
