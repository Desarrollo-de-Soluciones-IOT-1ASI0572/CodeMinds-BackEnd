package com.codeminds.edugo.vehicule.interfaces.rest.resources;

public record GeoPoint(double latitude, double longitude) {
    // Validación básica en el constructor
    public GeoPoint {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
    }
}
