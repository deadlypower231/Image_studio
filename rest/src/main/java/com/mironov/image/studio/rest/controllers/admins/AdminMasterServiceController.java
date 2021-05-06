package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.services.IMasterServicesService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/services")
public class AdminMasterServiceController {

    private static final String CURRENT_USER = "currentUser";
    private static final String SERVICES = "services";
    private static final String SERVICE = "service";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IMasterServicesService masterServicesService;

    public AdminMasterServiceController(IUserService userService, ISecurityService securityService, IMasterServicesService masterServicesService) {
        this.userService = userService;
        this.securityService = securityService;
        this.masterServicesService = masterServicesService;
    }

    @GetMapping
    public String getServices(Model model) {
        model.addAttribute(CURRENT_USER, this.securityService.findLoggedInUser());
        model.addAttribute(SERVICES, this.masterServicesService.getAll());
        model.addAttribute(SERVICE, new MasterServiceDto());
        return "admin/services";
    }

    @DeleteMapping
    @RequestMapping("/{idService}")
    public String deleteService(@PathVariable(name = "idService") Long idService) {
        this.masterServicesService.deleteService(idService);
        return "redirect:/admin/services";
    }

}
