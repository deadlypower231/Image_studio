package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.IdDto;
import com.mironov.image.studio.api.dto.SearchDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.api.services.IRoleService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/users")
public class AdminUserController {

    private static final String CURRENT_USER = "currentUser";
    private static final String ADMIN_USERS = "admin/users";
    private static final String REDIRECT_ADMIN_USERS = "redirect:/admin/users";
    private static final String ID = "id";
    private static final String USER = "user";
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
    public String getUsers(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        model.addAllAttributes(this.userService.findPaginatedUsers(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(ROLES, this.roleService.getAll());
        model.addAttribute(USER, new UserRolesDto());
        model.addAttribute(SEARCH, new SearchDto());
        model.addAttribute(ID, new IdDto());
        return ADMIN_USERS;
    }

    @PostMapping("/status")
    public String updateStatus(@ModelAttribute(name = "id") IdDto idDto) {
        this.userService.updateStatus(idDto.getId());
        return REDIRECT_ADMIN_USERS;
    }

    @PostMapping("/roles/{id}")
    public String updateRoles(@PathVariable(name = ID) long id, @ModelAttribute(USER) UserRolesDto userRolesDto) {
        this.userService.updateUserRoles(userRolesDto, id);
        return REDIRECT_ADMIN_USERS;
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute(name = ID) IdDto id) {
        this.userService.delete(id.getId());
        return REDIRECT_ADMIN_USERS;
    }

    @GetMapping("/search")
    public String searchUser(@ModelAttribute(SEARCH) @Valid SearchDto searchDto,
                             BindingResult bindingResult, Model model,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        if (bindingResult.hasErrors()) {
            model.addAttribute(SEARCH, searchDto);
            model.addAllAttributes(this.userService.findPaginatedUsers(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
            return ADMIN_USERS;
        }
        model.addAllAttributes(this.userService.findPaginatedUsersSearch(PageRequest.of(page.orElse(1) - 1, size.orElse(10)), searchDto.getText()));
        model.addAttribute(SEARCH, new SearchDto());
        model.addAttribute(ROLES, this.roleService.getAll());
        model.addAttribute(USER, new UserRolesDto());
        return ADMIN_USERS;
    }

}
