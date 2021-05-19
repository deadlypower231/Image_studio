package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.services.ISecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final ISecurityService securityService;

    public MainController(ISecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        CurrentUserDto currentUser = this.securityService.findLoggedInUser();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
        }
        return "main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "signup/login";
    }

    @GetMapping(value = "/403")
    public String exception(){
        return "error/403";
    }

}
