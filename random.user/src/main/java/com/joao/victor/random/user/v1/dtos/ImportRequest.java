package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

import java.util.List;

public @Data class ImportRequest {

    private List<CreateUserRequest> results;
}
