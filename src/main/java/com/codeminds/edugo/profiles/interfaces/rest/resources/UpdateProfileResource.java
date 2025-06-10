package com.codeminds.edugo.profiles.interfaces.rest.resources;

public record UpdateProfileResource(String full_name,
        String email,
        String phone_number,
        String gender,
        String profile_picture_url) {

}
