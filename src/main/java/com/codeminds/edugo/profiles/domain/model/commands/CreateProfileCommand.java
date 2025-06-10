package com.codeminds.edugo.profiles.domain.model.commands;

public record CreateProfileCommand(
        Long userId,
        String full_name,
        String email,
        String phone_number,
        String gender,
        String profile_picture_url) {
}
