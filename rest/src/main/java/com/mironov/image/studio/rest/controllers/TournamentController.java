package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.ITournamentService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private static final String CURRENT_USER = "currentUser";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final ITournamentService tournamentService;

    public TournamentController(IUserService userService, ISecurityService securityService, ITournamentService tournamentService) {
        this.userService = userService;
        this.securityService = securityService;
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getTournaments (Model model){
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournaments", this.tournamentService.getAll());
        return "tournaments";
    }

    @GetMapping
    @RequestMapping("/moreInfo/{id}")
    public String getTournament(@PathVariable(name = "id") long id, Model model){
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournament", this.tournamentService.get(id));
        return "tournamentsMoreInfo";
    }

    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());
    }

}
