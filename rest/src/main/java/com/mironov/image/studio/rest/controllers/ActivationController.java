package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.services.IActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activation")
public class ActivationController {

    private final IActivationService activationService;

    public ActivationController(IActivationService activationService) {
        this.activationService = activationService;
    }

    //todo make a page when the email was sent

    @GetMapping
    @RequestMapping("/{activation}")
    public String activation(@PathVariable(name = "activation") String activation, Model model) {
        UserCreateDto user = this.activationService.activationUser(activation);
        if (user == null) {
            return "signup/failedActivation";
        }
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        return "signup/successfulActivation";
    }


}
