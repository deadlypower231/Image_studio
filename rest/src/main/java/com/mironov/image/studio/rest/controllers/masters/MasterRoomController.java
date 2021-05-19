package com.mironov.image.studio.rest.controllers.masters;

import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.ITournamentService;
import com.mironov.image.studio.enums.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/master")
public class MasterRoomController {

    private final ISecurityService securityService;
    private final ITournamentService tournamentService;

    public MasterRoomController(ISecurityService securityService, ITournamentService tournamentService) {
        this.securityService = securityService;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournaments")
    public String orderToTournamentPage(Model model) {
        model.addAttribute("currentUser", this.securityService.findLoggedInUser());
        model.addAttribute("ordersForTournament",
                this.tournamentService.getTournamentsWithMaster(this.securityService.findLoggedInUser().getId(), Status.ACTIVE));
        model.addAttribute("ordersForTournamentArchive",
                this.tournamentService.getTournamentsWithMaster(this.securityService.findLoggedInUser().getId(), Status.INACTIVE));
        return "ordersToTournament";
    }

}
