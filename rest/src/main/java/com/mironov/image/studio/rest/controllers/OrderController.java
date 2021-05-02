package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.IdDataOrderDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final String CURRENT_USER = "currentUser";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IScheduleService scheduleService;
    private final ITournamentService tournamentService;
    private final IMasterServicesService masterServicesService;
    private final IOrderService orderService;

    public OrderController(IUserService userService, ISecurityService securityService, IScheduleService scheduleService,
                           ITournamentService tournamentService, IMasterServicesService masterServicesService,
                           IOrderService orderService) {
        this.userService = userService;
        this.securityService = securityService;
        this.scheduleService = scheduleService;
        this.tournamentService = tournamentService;
        this.masterServicesService = masterServicesService;
        this.orderService = orderService;
    }

    @GetMapping
    public String orderPage(Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournaments", this.tournamentService.getAll());
        return "orders";
    }

    @GetMapping
    @RequestMapping("/{idTournament}")
    public String orderTournamentPage(@PathVariable(name = "idTournament") long idTournament, Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournament", this.tournamentService.get(idTournament));
        return "ordersTournament";
    }

    @GetMapping
    @RequestMapping("/{idTournament}/{idMaster}")
    public String orderTournamentMasterPage(@PathVariable(name = "idTournament") long idTournament,
                                            @PathVariable(name = "idMaster") long idMaster,
                                            Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute("tournament", this.tournamentService.get(idTournament));
        model.addAttribute("master", this.userService.getUser(idMaster));
        model.addAttribute("schedules", this.scheduleService.getSchedulesByIdTournamentIdMasterIsActive(idTournament, idMaster));
        model.addAttribute("order", new IdDataOrderDto(idTournament, idMaster));
        return "ordersTournamentSchedules";
    }

    @PostMapping
    @RequestMapping("/registration")
    public String createOrder(@ModelAttribute("order") @Valid IdDataOrderDto idDataOrderDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute(CURRENT_USER, getCurrentUser());
            model.addAttribute("tournament", this.tournamentService.get(idDataOrderDto.getIdTournament()));
            model.addAttribute("master", this.userService.getUser(idDataOrderDto.getIdMaster()));
            model.addAttribute("schedules", this.scheduleService.getSchedulesByIdTournamentIdMasterIsActive(idDataOrderDto.getIdTournament(), idDataOrderDto.getIdMaster()));
            model.addAttribute("order", idDataOrderDto);
            return "ordersTournamentSchedules";
        }
        this.orderService.createOrder(idDataOrderDto, getCurrentUser());
        return "redirect:/tournaments";
    }


    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());
    }

}
