package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.SendMessageDto;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ISecurityService securityService;
    private final IUserService userService;

    public UserController(ISecurityService securityService, IUserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("currentUser", this.securityService.findLoggedInUser());
        model.addAttribute("user", this.userService.getUser(id));
        model.addAttribute("sendMessage", new SendMessageDto());
        return "user";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute("sendMessage") SendMessageDto sendMessage, Model model) {
        this.userService.sendMessage(this.securityService.findLoggedInUser(), sendMessage);
        return String.format("redirect:/users/%s", sendMessage.getId());
    }

}
