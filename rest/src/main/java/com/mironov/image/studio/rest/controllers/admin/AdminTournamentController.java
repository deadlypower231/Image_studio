package com.mironov.image.studio.rest.controllers.admin;

import com.mironov.image.studio.api.dto.IdUsersDto;
import com.mironov.image.studio.api.dto.TimeDto;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.api.dto.UserDto;
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
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournaments", this.tournamentService.getAll());
        model.addAttribute("masters", this.userService.getAllMasters());
        model.addAttribute("idUsers", new IdUsersDto());
        model.addAttribute("time", new TimeDto());
        return "admin/tournaments";
    }

    @GetMapping("/create")
    public String createTournament(Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournament", new TournamentDto());
        return "admin/tournamentsCreate";
    }

    @GetMapping("/delete")
    public String deleteTournament(Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournaments", this.tournamentService.getAll());
        return "admin/tournamentsDelete";
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public String deleteTournamentById (@PathVariable(name = "id") long id){
        this.tournamentService.deleteById(id);
        return "redirect:/admin/tournaments/delete";
    }

    @PostMapping(value = "/create")
    public String saveTournament(@ModelAttribute("tournament") TournamentDto tournament,
                                 @RequestParam(value = "file", required = false)
                                         MultipartFile file) {
        this.tournamentService.saveTournament(tournament, file);
        return "redirect:/admin/tournaments";
    }

    @PostMapping
    @RequestMapping(value = "/{id}")
    public String saveAddMastersToTournament(@ModelAttribute("idUsers") IdUsersDto idUsersDto,
                                             @PathVariable(name = "id") long id) {
        this.tournamentService.addMastersToTournament(id, idUsersDto);
        return "redirect:/admin/tournaments";
    }

    @PostMapping
    @RequestMapping(value = "/schedule/{idTournament}/{idMaster}")
    public String saveAddScheduleToMasterWithTournament(@ModelAttribute("time") TimeDto timeDto,
                                                        @PathVariable(name = "idTournament") long idTournament,
                                                        @PathVariable(name = "idMaster") long idMaster) {
        this.tournamentService.saveScheduleToMasterWithTournament(idTournament, idMaster, timeDto);
        return String.format("redirect:/admin/tournaments/%d/%d", idTournament, idMaster);
    }

    @GetMapping
    @RequestMapping(value = "/{idTournament}/{idMaster}")
    public String getTournamentWithMaster(Model model,
                                          @PathVariable(name = "idTournament") long idTournament,
                                          @PathVariable(name = "idMaster") long idMaster) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournament", this.tournamentService.get(idTournament));
        model.addAttribute("master", this.userService.getUser(idMaster));
        model.addAttribute("schedules", this.scheduleService.getSchedulesByIdTournamentIdMaster(idTournament, idMaster));
        model.addAttribute("time", new TimeDto());

        return "admin/tournamentsSchedule";
    }

    @DeleteMapping
    @RequestMapping(value = "/schedule/delete/{id}/{idTournament}/{idMaster}")
    public String deleteSchedule(@PathVariable(name = "id") long id,
                                 @PathVariable(name = "idTournament") long idTournament,
                                 @PathVariable(name = "idMaster") long idMaster) {
        this.scheduleService.deleteById(id);
        return String.format("redirect:/admin/tournaments/%d/%d",idTournament, idMaster);
    }

    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());

    }

}
