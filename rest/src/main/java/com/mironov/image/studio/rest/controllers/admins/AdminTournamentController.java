package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.api.services.IScheduleService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.ITournamentService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/tournaments")
public class AdminTournamentController {

    private static final String CURRENT_USER = "currentUser";
    private static final String TOURNAMENTS = "tournaments";
    private static final String TOURNAMENT = "tournament";
    private static final String SCHEDULES = "schedules";
    private static final String MASTER = "master";
    private static final String MASTERS = "masters";
    private static final String TIME = "time";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IScheduleService scheduleService;
    private final ITournamentService tournamentService;

    public AdminTournamentController(IUserService userService, ISecurityService securityService, IScheduleService scheduleService, ITournamentService tournamentService) {
        this.userService = userService;
        this.securityService = securityService;
        this.scheduleService = scheduleService;
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public String setTournaments(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENTS, this.tournamentService.getAll());
        model.addAttribute(MASTERS, this.userService.getAllMasters());
        model.addAttribute("idUsers", new IdUsersDto());
        model.addAttribute(TIME, new TimeDto());
        return "admin/tournaments";
    }

    @GetMapping("/create")
    public String createTournament(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENT, new TournamentDto());
        return "admin/tournamentsCreate";
    }

    @GetMapping("/delete")
    public String deleteTournament(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENTS, this.tournamentService.getAll());
        model.addAttribute("id", new IdDto());
        return "admin/tournamentsDelete";
    }

    @PostMapping("/delete")
    public String deleteTournamentById(@ModelAttribute("id") IdDto id) {
        this.tournamentService.deleteById(id.getId());
        return "redirect:/admin/tournaments/delete";
    }

    @PostMapping(value = "/create")
    public String saveTournament(@ModelAttribute(TOURNAMENT) TournamentDto tournament,
                                 @RequestParam(value = "file", required = false)
                                         MultipartFile file) {
        this.tournamentService.saveTournament(tournament, file);
        return "redirect:/admin/tournaments";
    }

    @PostMapping
    public String saveAddMastersToTournament(@ModelAttribute("idUsers") IdUsersDto idUsersDto) {
        this.tournamentService.addMastersToTournament(idUsersDto.getId(), idUsersDto);
        return "redirect:/admin/tournaments";
    }

    @PostMapping("/schedule/{idTournament}/{idMaster}")
    public String saveAddScheduleToMasterWithTournament(@ModelAttribute(TIME) TimeDto timeDto,
                                                        @PathVariable(name = "idTournament") long idTournament,
                                                        @PathVariable(name = "idMaster") long idMaster) {
        this.tournamentService.saveScheduleToMasterWithTournament(idTournament, idMaster, timeDto);
        return String.format("redirect:/admin/tournaments/%d/%d", idTournament, idMaster);
    }

    @GetMapping("/{idTournament}/{idMaster}")
    public String getTournamentWithMaster(Model model,
                                          @PathVariable(name = "idTournament") long idTournament,
                                          @PathVariable(name = "idMaster") long idMaster) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENT, this.tournamentService.get(idTournament));
        model.addAttribute(MASTER, this.userService.getUser(idMaster));
        model.addAttribute(SCHEDULES, this.scheduleService.getSchedulesByIdTournamentIdMaster(idTournament, idMaster));
        model.addAttribute(TIME, new TimeDto());
        model.addAttribute("id", new IdDto());
        return "admin/tournamentsSchedule";
    }

    @PostMapping("/schedule/delete/{idTournament}/{idMaster}")
    public String deleteSchedule(@ModelAttribute("id")IdDto id,
                                 @PathVariable(name = "idTournament") long idTournament,
                                 @PathVariable(name = "idMaster") long idMaster) {
        this.scheduleService.deleteById(id.getId());
        return String.format("redirect:/admin/tournaments/%d/%d", idTournament, idMaster);
    }

}
