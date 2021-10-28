package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

public @Data class CreateUserRequest {

    private String gender;

    private String email;

    private String phone;

    private String cell;

    private String nat;

    private CreateNameRequest name;

    private CreateLocationRequest location;

    private CreateLoginRequest login;

    private DobRequest dob;

    private RegisteredRequest registered;

    private IdRequest id;

    private PictureRequest picture;
}
