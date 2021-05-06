package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    private static final String USER = "user";

    private final IUserService userService;
    private final ISecurityService securityService;

    public SignupController(IUserService userService, ISecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String newUser(@ModelAttribute(USER) UserCreateDto userDto) {
        return "signup/signup";
    }

    //todo

    @PostMapping
    public String createUser(@ModelAttribute(USER) @Valid UserCreateDto user,
                             BindingResult bindingResult, Model model) {
        boolean checkEmail = this.userService.findUserByEmail(user.getEmail());
        boolean checkUsername = this.userService.findUserByName(user.getUsername());
        boolean checkNumberPhone = this.userService.findUserByNumberPhone(user.getPhone());
        if (checkNumberPhone){
            bindingResult.rejectValue("phone", "phone", "Этот номер телефона уже занят!");
        }
        if (checkUsername){
            bindingResult.rejectValue("username", "username", "Этот логин уже занят!");
        }
        if (checkEmail){
            bindingResult.rejectValue("email", "email", "Эта электронная почта уже занята!");
        }
        if (bindingResult.hasErrors()) {
            return "signup/signup";
        }
            this.userService.createUser(user);
        return "redirect:/";
    }

}
