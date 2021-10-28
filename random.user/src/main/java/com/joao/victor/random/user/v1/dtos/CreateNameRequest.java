package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

public @Data class CreateNameRequest {

    private String title;

    private String first;

    private String last;
}
