package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

public @Data class TimeZoneRequest {

    private String offset;

    private String description;
}
