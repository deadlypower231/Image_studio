package com.mironov.image.studio.rest.controllers;

import com.mironov.image.studio.api.services.IOrderService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class MyOrderController {

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IOrderService orderService;

    public MyOrderController(IUserService userService, ISecurityService securityService, IOrderService orderService) {
        this.userService = userService;
        this.securityService = securityService;
        this.orderService = orderService;
    }

    @GetMapping
    public String ordersPage(Model model) {
        model.addAttribute("currentUser", this.securityService.findLoggedInUser());
        model.addAttribute("orders", this.orderService.getAllOrdersByCurrentUser(this.securityService.findLoggedInUser().getId()));
        return "myOrders";
    }


}
