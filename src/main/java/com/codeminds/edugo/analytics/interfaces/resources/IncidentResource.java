package com.codeminds.edugo.analytics.interfaces.resources;

public record IncidentResource(
        boolean detour,
        boolean lateness,
        boolean speeding
) {
}
