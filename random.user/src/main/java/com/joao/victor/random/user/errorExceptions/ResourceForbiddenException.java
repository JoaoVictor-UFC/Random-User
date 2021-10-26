package com.joao.victor.parcel.simulator.errorExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceForbiddenException(String message) {
        super(message);
    }

}
