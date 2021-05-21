package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.IdDto;
import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.services.IMasterServicesService;
import com.mironov.image.studio.api.services.ISecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/services")
public class AdminMasterServiceController {

    private static final String CURRENT_USER = "currentUser";
    private static final String SERVICES = "services";
    private static final String SERVICE = "service";

    private final ISecurityService securityService;
    private final IMasterServicesService masterServicesService;

    public AdminMasterServiceController(ISecurityService securityService, IMasterServicesService masterServicesService) {
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

    @PostMapping("/add")
    public String addService(@ModelAttribute(name = "service") MasterServiceDto masterServiceDto){
        this.masterServicesService.createService(masterServiceDto, masterServiceDto.getIdMaster());
        return "redirect:/admin/service";
    }

    @PostMapping("/delete")
    public String deleteService(@ModelAttribute(name = "id") IdDto id) {
        this.masterServicesService.deleteService(id.getId());
        return "redirect:/admin/services";
    }

}
