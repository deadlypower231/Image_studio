package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.entities.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return this.userService.getUser(id);
    }
}
