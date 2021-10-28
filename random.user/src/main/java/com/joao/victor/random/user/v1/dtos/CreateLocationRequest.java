package com.joao.victor.random.user.v1.dtos;

import com.joao.victor.random.user.v1.entities.CoordinatesEntity;
import com.joao.victor.random.user.v1.entities.TimezoneEntity;
import lombok.Data;

public @Data class CreateLocationRequest {

    private String street;

    private String city;

    private String state;

    private String postcode;

    private CoordinatesRequest coordinates;

    private TimeZoneRequest timezone;
}
