package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final IUserService userService;
    private final ISecurityService securityService;

    public MainController(IUserService userService, ISecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        UserDto currentUser = getCurrentUser();
        if (currentUser != null) {
            model.addAttribute("currentUser", currentUser);
        }
        return "main";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    private UserDto getCurrentUser() {
        if (this.securityService.findLoggedInUser().equals("anonymousUser")) {
            return null;
        }
        return this.userService.findUserByName(this.securityService.findLoggedInUser());
    }
}
