package com.joao.victor.random.user.v1.controller;

import com.joao.victor.random.user.errorExceptions.MessageErrorCustom;
import com.joao.victor.random.user.v1.dtos.CreateUserRequest;
import com.joao.victor.random.user.v1.entities.UserEntity;
import com.joao.victor.random.user.v1.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(value = "User Service", tags = { "User Service" })
@Valid
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired private UserService userService;

    @ApiOperation(value = "Create User",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @PostMapping(consumes = { "application/json", "application/xml" })
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid CreateUserRequest req){
        UserEntity user = userService.createUser(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/id").buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
