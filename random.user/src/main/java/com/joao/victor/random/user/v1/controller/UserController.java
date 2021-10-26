package com.joao.victor.random.user.v1.controller;

import com.joao.victor.random.user.v1.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "User Service", tags = { "User Service" })
@RestController
@Valid
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;
}
