package com.mironov.image.studio.rest.controllers.admin;

import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.IMasterServicesService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/services")
public class AdminMasterServiceController {

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IMasterServicesService masterServicesService;

    public AdminMasterServiceController(IUserService userService, ISecurityService securityService, IMasterServicesService masterServicesService) {
        this.userService = userService;
        this.securityService = securityService;
        this.masterServicesService = masterServicesService;
    }

    @GetMapping
    public String getServices(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("services", this.masterServicesService.getAll());
        model.addAttribute("service", new MasterServiceDto());
        return "admin/services";
    }

    @DeleteMapping
    @RequestMapping("/{idService}")
    public String deleteService(@PathVariable(name = "idService") Long idService){
        this.masterServicesService.deleteService(idService);
        return "redirect:/admin/services";
    }

    private UserDto getCurrentUser() {
        return this.userService.findUserByName(this.securityService.findLoggedInUser());
    }

}
