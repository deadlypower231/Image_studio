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
    private static final String TOURNAMENTS = "tournaments";
    private static final String TOURNAMENT = "tournament";
    private static final String ORDERS = "orders";
    private static final String ORDER = "order";
    private static final String SCHEDULES = "schedules";
    private static final String MASTER = "master";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IScheduleService scheduleService;
    private final ITournamentService tournamentService;
    private final IOrderService orderService;

    public OrderController(IUserService userService, ISecurityService securityService, IScheduleService scheduleService,
                           ITournamentService tournamentService, IOrderService orderService) {
        this.userService = userService;
        this.securityService = securityService;
        this.scheduleService = scheduleService;
        this.tournamentService = tournamentService;
        this.orderService = orderService;
    }

    @GetMapping
    public String orderPage(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENTS, this.tournamentService.getAll());
        return ORDERS;
    }

    @GetMapping
    @RequestMapping("/{idTournament}")
    public String orderTournamentPage(@PathVariable(name = "idTournament") long idTournament, Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENT, this.tournamentService.get(idTournament));
        return "ordersTournament";
    }

    @GetMapping
    @RequestMapping("/{idTournament}/{idMaster}")
    public String orderTournamentMasterPage(@PathVariable(name = "idTournament") long idTournament,
                                            @PathVariable(name = "idMaster") long idMaster,
                                            Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(TOURNAMENT, this.tournamentService.get(idTournament));
        model.addAttribute(MASTER, this.userService.getUser(idMaster));
        model.addAttribute(SCHEDULES, this.scheduleService.getSchedulesByIdTournamentIdMasterIsActive(idTournament, idMaster));
        model.addAttribute(ORDER, new IdDataOrderDto(idTournament, idMaster));
        return "ordersTournamentSchedules";
    }

    @PostMapping
    @RequestMapping("/registration")
    public String createOrder(@ModelAttribute(ORDER) @Valid IdDataOrderDto idDataOrderDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
            model.addAttribute(TOURNAMENT, this.tournamentService.get(idDataOrderDto.getIdTournament()));
            model.addAttribute(MASTER, this.userService.getUser(idDataOrderDto.getIdMaster()));
            model.addAttribute(SCHEDULES, this.scheduleService.getSchedulesByIdTournamentIdMasterIsActive(idDataOrderDto.getIdTournament(), idDataOrderDto.getIdMaster()));
            model.addAttribute(ORDER, idDataOrderDto);
            return "ordersTournamentSchedules";
        }
        this.orderService.createOrder(idDataOrderDto, this.securityService.findLoggedInUser().getId());
        return "redirect:/tournaments";
    }

}
