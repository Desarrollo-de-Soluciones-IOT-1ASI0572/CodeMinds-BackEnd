package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record ProfileResource(Long id, Long userId, String fullName, String email, String phone_number, String gender,
        String profile_picture_url) {
}
