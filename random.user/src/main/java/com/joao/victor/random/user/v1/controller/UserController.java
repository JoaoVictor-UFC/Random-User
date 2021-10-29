package com.joao.victor.random.user.v1.controller;

import com.joao.victor.random.user.errorExceptions.MessageErrorCustom;
import com.joao.victor.random.user.v1.dtos.CreateUserRequest;
import com.joao.victor.random.user.v1.dtos.UserResponse;
import com.joao.victor.random.user.v1.entities.UserEntity;
import com.joao.victor.random.user.v1.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Api(value = "User Service", tags = { "User Service" })
@Valid
@RequestMapping("/")
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
    @PostMapping(value = "create", consumes = { "application/json", "application/xml" })
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid CreateUserRequest req){
        UserEntity user = userService.createUser(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/id").buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Message Challenge",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = String.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<String> message (){
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return ResponseEntity.ok().body("REST Back-end Challenge " + dateNow.format(formatter) + " Running");
    }

    @ApiOperation(value = "Import Users",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @PostMapping(value = "users/import")
    public ResponseEntity<?> importDateUsers(@PathVariable Long id) throws Exception {
        userService.importForApiRandomUser(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update User",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @PutMapping(value = "users/{userId}", consumes = { "application/json", "application/xml" })
    public ResponseEntity<?> updateUser(@RequestBody CreateUserRequest request,
                                        @PathVariable Long userId){
        return ResponseEntity.ok().body(userService.updateUser(userId, request));
    }

    @ApiOperation(value = "Delete User",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @DeleteMapping(value = "users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Find User By Id",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @GetMapping(value = "users/{userId}", produces = { "application/json", "application/xml" })
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long userId){
        return ResponseEntity.ok().body(userService.findUserById(userId));
    }

    @ApiOperation(value = "Find All Users",notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = UserEntity.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZATED", response = MessageErrorCustom.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = MessageErrorCustom.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = MessageErrorCustom.class)
    })
    @GetMapping(value = "users", produces = { "application/json", "application/xml" })
    public ResponseEntity<Page<UserEntity>> findAllUsers
            (@RequestParam(value = "page", required = false, defaultValue = "0") int page,
             @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> res = userService.findAllUsers(pageRequest);
        return ResponseEntity.ok().body(res);
    }

}
