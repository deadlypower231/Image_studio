package com.mironov.image.studio.rest.controllers.admin;

import com.mironov.image.studio.api.dto.SearchDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.api.services.IRoleService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/users")
public class AdminUserController {

    private static final String CURRENT_USER = "currentUser";

    private final IRoleService roleService;
    private final IUserService userService;
    private final ISecurityService securityService;

    public AdminUserController(IRoleService roleService, IUserService userService, ISecurityService securityService) {
        this.roleService = roleService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("user", new UserRolesDto());
        model.addAttribute("users", this.userService.getAll());
        model.addAttribute("roles", this.roleService.getAll());
        model.addAttribute("search", new SearchDto());
        return "admin/users";
    }

    @PostMapping
    @RequestMapping(value = "/roles/{id}")
    public String updateRoles(@PathVariable(name = "id") long id, @ModelAttribute("user") UserRolesDto userRolesDto) {
        this.userService.updateUserRoles(userRolesDto, id);
        return "redirect:/admin/users";
    }

    @PostMapping
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        this.userService.delete(id);
        return "redirect:/admin/users";
    }


    @GetMapping("/search")
    public String searchUser(@ModelAttribute("search") @Valid SearchDto searchDto, BindingResult bindingResult, Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", this.userService.getAll());
            model.addAttribute("search", searchDto);
            return "admin/users";
        }
        model.addAttribute("users", this.userService.searchUsers(searchDto.getText()));
        model.addAttribute("search", new SearchDto());
        return "admin/users";
    }

    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());

    }

}
