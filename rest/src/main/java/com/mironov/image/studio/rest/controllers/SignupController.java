package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserDto;
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

    private final IUserService userService;
    private final ISecurityService securityService;

    public SignupController(IUserService userService, ISecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String signupUser(@ModelAttribute("user") UserDto userDto) {
        return "signup/signup";
    }

    @PostMapping
    public String submitCreatingUser(@ModelAttribute("user") @Valid UserDto user,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup/signup";
        }
        try {
            UserDto userDto = this.userService.createUser(user);
            this.userService.addUserRole(userDto);
            securityService.autoLogin(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/createEntityError";
        }
        return "main";
    }

}
