package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.dto.IdDto;
import com.mironov.image.studio.api.services.IOrderService;
import com.mironov.image.studio.api.services.ISecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class MyOrderController {

    private final ISecurityService securityService;
    private final IOrderService orderService;

    public MyOrderController(ISecurityService securityService, IOrderService orderService) {
        this.securityService = securityService;
        this.orderService = orderService;
    }

    @GetMapping
    public String ordersPage(Model model) {
        model.addAttribute("currentUser", this.securityService.findLoggedInUser());
        model.addAttribute("ordersActive", this.orderService.getAllActiveOrdersByCurrentUser(this.securityService.findLoggedInUser().getId()));
        model.addAttribute("ordersArchive", this.orderService.getAllArchiveOrdersByCurrentUser(this.securityService.findLoggedInUser().getId()));
        model.addAttribute("id", new IdDto());
        return "myOrders";
    }

    @PostMapping
    @RequestMapping("/delete")
    public String deleteOrder(@ModelAttribute(name = "id") IdDto idDto) {
        this.orderService.deleteOrder(idDto.getId());
        return "redirect:/orders";
    }

}
