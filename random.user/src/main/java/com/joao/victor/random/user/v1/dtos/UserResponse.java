package com.joao.victor.random.user.v1.dtos;

import com.joao.victor.random.user.v1.entities.*;
import lombok.Data;

public @Data class UserResponse {

    private String gender;

    private String email;

    private String phone;

    private String cell;

    private String nat;

    private NameEntity name;

    private LocationEntity location;

    private LoginEntity login;

    private DobEntity dob;

    private RegisteredEntity registered;

    private IdEntity id;

    private PictureEntity picture;
}
