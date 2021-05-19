package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.DescriptionDto;
import com.mironov.image.studio.api.dto.IdDto;
import com.mironov.image.studio.api.dto.UserUpdateDto;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/settings")
public class SettingController {

    private static final String CURRENT_USER = "currentUser";
    private static final String REDIRECT_SETTINGS = "redirect:/settings";
    private static final String SETTINGS = "settings";
    private static final String USER = "user";

    private final IUserService userService;
    private final ISecurityService securityService;

    public SettingController(IUserService userService, ISecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String settingsPage(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(USER, this.userService.getUser(this.securityService.findLoggedInUser().getId()));
        model.addAttribute("id", new IdDto());
        model.addAttribute("description", new DescriptionDto());
        return SETTINGS;
    }

    @PostMapping("/updateFile")
    public String updateImage(@RequestParam(value = "file",required = false) MultipartFile multipartFile, IdDto id){
        this.userService.updateUserImage(multipartFile, id.getId());
        return REDIRECT_SETTINGS;
    }

    @PostMapping("/updateDescription")
    public String updateDescription(@ModelAttribute(name = "description") DescriptionDto descriptionDto){
        this.userService.updateUserDescription(descriptionDto, this.securityService.findLoggedInUser().getId());
        return REDIRECT_SETTINGS;
    }

    @PostMapping
    public String saveSettings(@ModelAttribute(USER) @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, Model model) {
        if (this.userService.findUserByName(userUpdateDto.getUsername()) &&
                !this.securityService.findLoggedInUser().getUsername().equals(userUpdateDto.getUsername())) {
            bindingResult.rejectValue("username", "username", "Этот логин уже занят!");
        }
        if (this.userService.findUserByEmail(userUpdateDto.getEmail()) &&
                !this.securityService.findLoggedInUser().getEmail().equals(userUpdateDto.getEmail())) {
            bindingResult.rejectValue("email", "email", "Эта электронная почта уже занята!");
        }
        if (this.userService.findUserByNumberPhone(userUpdateDto.getPhone()) &&
                !this.securityService.findLoggedInUser().getNumberPhone().equals(userUpdateDto.getPhone())) {
            bindingResult.rejectValue("phone", "phone", "Этот номер телефона уже занят!");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
            model.addAttribute(USER, userUpdateDto);
            return SETTINGS;
        }
        this.userService.updateUser(userUpdateDto);
        return REDIRECT_SETTINGS;
    }

}
