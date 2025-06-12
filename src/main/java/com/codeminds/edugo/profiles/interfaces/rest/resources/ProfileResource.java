package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record ProfileResource(Long id, Long userId, String fullName, String email, String mobileNumber, String address,
        String gender, String photoUrl, String role) {
}
