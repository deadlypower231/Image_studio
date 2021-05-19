package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.ITournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private static final String CURRENT_USER = "currentUser";
    private static final String TOURNAMENTS = "tournaments";
    private static final String TOURNAMENT = "tournament";

    private final ISecurityService securityService;
    private final ITournamentService tournamentService;

    public TournamentController(ISecurityService securityService, ITournamentService tournamentService) {
        this.securityService = securityService;
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String getTournaments(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENTS, this.tournamentService.getAll());
        return TOURNAMENTS;
    }

    @GetMapping("/moreInfo/{id}")
    public String getTournament(@PathVariable(name = "id") long id, Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENT, this.tournamentService.get(id));
        return "tournamentsMoreInfo";
    }

}
