package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.api.services.IRoleService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.api.services.ITournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class AdminControllers {
    private final IRoleService roleService;
    private final IUserService userService;
    private final ISecurityService securityService;
    private final ITournamentService tournamentService;

    public AdminControllers(IRoleService roleService, IUserService userService, ISecurityService securityService, ITournamentService tournamentService) {
        this.roleService = roleService;
        this.userService = userService;
        this.securityService = securityService;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("user", new UserRolesDto());
        model.addAttribute("users", this.userService.getAll());
        model.addAttribute("roles", this.roleService.getAll());
        return "admin/users";
    }

    @GetMapping("/tournaments")
    public String setTournaments(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("tournaments", this.tournamentService.getAll());
        return "admin/tournaments";
    }

    @GetMapping("/tournaments/create")
    public String createTournament(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("tournament", new TournamentDto());
        return "admin/tournamentsCreate";
    }

    @PostMapping(value = "/tournaments/create")
    public String saveTournament(@ModelAttribute("tournament") TournamentDto tournament,
                                 @RequestParam(value = "file", required = false)
                                         MultipartFile file) {
        this.tournamentService.saveTournament(tournament, file);
        return "redirect:/admin/tournaments";
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
