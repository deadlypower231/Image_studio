package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.EmailDto;
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
@RequestMapping("/login")
public class LoginController {

    private final IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage(@ModelAttribute("email") EmailDto email) {
        return "signup/forgotPassword";
    }

    //todo make redirect to login page

    @PostMapping
    @RequestMapping("/forgotPassword")
    public String sendNewPasswordToUser(@ModelAttribute("email") @Valid EmailDto email, BindingResult bindingResult, Model model) {
        if (!this.userService.createNewPassword(email)) {
            bindingResult.rejectValue("email", "email", "Эта электронная почта не найдена!");
        }
        if (bindingResult.hasErrors()) {
            return "signup/forgotPassword";
        }
        return "signup/login";

    }

}
