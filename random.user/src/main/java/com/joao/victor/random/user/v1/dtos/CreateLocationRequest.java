package com.joao.victor.random.user.v1.dtos;

import lombok.Data;

public @Data class CreateLocationRequest {

    private StreetRequest street;

    private String city;

    private String state;

    private String postcode;

    private CoordinatesRequest coordinates;

    private TimeZoneRequest timezone;
}
