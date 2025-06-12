package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record CreateProfileResource(String fullName, String email, String mobileNumber, String address, String gender,
                String photoUrl) {
}
