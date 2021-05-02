package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return this.userService.getUser(id);
    }
}
