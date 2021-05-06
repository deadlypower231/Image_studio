package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/masters")
public class MasterController {

    private static final String CURRENT_USER = "currentUser";
    private static final String MASTERS = "masters";

    private final IUserService userService;
    private final ISecurityService securityService;

    public MasterController(IUserService userService, ISecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String getMasters(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(MASTERS, this.userService.getAllMasters());
        return MASTERS;
    }

}
