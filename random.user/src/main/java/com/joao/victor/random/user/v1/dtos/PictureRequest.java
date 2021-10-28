package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

public @Data class PictureRequest {

    private String large;

    private String medium;

    private String thumbnail;
}
