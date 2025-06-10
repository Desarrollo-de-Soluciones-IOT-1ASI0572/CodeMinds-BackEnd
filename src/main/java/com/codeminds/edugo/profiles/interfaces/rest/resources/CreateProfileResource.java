package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        Long userId,
        String fullName,
        String email,
        String phone_number,
        String gender,
        String profile_picture_url) {
}
