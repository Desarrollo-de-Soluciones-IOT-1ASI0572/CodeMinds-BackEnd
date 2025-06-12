package com.codeminds.edugo.profiles.domain.model.commands;

public record CreateProfileCommand(String fullName, String email, String mobileNumber, String address, String gender,
                String photoUrl) {
}
