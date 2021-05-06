package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.IdDto;
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
    private static final String ADMIN_USERS = "admin/users";
    private static final String REDIRECT_ADMIN_USERS = "redirect:/admin/users";
    private static final String ID = "id";
    private static final String USER = "user";
    private static final String USERS = "users";
    private static final String ROLES = "roles";
    private static final String SEARCH = "search";

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
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(USER, new UserRolesDto());
        model.addAttribute(USERS, this.userService.getAll());
        model.addAttribute(ROLES, this.roleService.getAll());
        model.addAttribute(SEARCH, new SearchDto());
        model.addAttribute(ID, new IdDto());
        return ADMIN_USERS;
    }

    @PostMapping
    @RequestMapping(value = "/roles/{id}")
    public String updateRoles(@PathVariable(name = ID) long id, @ModelAttribute(USER) UserRolesDto userRolesDto) {
        this.userService.updateUserRoles(userRolesDto, id);
        return REDIRECT_ADMIN_USERS;
    }

    //todo field hidden

    @PostMapping
    @RequestMapping(value = "/delete")
    public String deleteUser(@ModelAttribute(name = ID) IdDto id) {
        this.userService.delete(id.getId());
        return REDIRECT_ADMIN_USERS;
    }


    @GetMapping("/search")
    public String searchUser(@ModelAttribute(SEARCH) @Valid SearchDto searchDto, BindingResult bindingResult, Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        if (bindingResult.hasErrors()) {
            model.addAttribute(USERS, this.userService.getAll());
            model.addAttribute(SEARCH, searchDto);
            return ADMIN_USERS;
        }
        model.addAttribute(USERS, this.userService.searchUsers(searchDto.getText()));
        model.addAttribute(SEARCH, new SearchDto());
        return ADMIN_USERS;
    }

}
