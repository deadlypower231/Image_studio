package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.api.services.IRoleService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminControllers {
    private final IRoleService roleService;
    private final IUserService userService;
    private final ISecurityService securityService;

    public AdminControllers(IRoleService roleService, IUserService userService, ISecurityService securityService) {
        this.roleService = roleService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("user", new UserRolesDto());
        model.addAttribute("users", this.userService.getAll());
        model.addAttribute("roles", this.roleService.getAll());
        return "admin/users";
    }

    @PostMapping
    @RequestMapping(value = "/users/roles/{id}")
    public String updateRoles(@PathVariable(name = "id") long id, @ModelAttribute("user") UserRolesDto userRolesDto) {
        this.userService.updateUserRoles(userRolesDto, id);
        return "redirect:/admin/users";
    }

    @PostMapping
    @RequestMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        this.userService.delete(id);
        return "redirect:/admin/users";
    }

    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());
    }

}
