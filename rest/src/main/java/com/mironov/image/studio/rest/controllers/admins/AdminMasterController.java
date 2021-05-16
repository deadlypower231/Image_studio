package com.mironov.image.studio.rest.controllers.admins;

import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.api.services.IMasterServicesService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/masters")
public class AdminMasterController {

    private static final String ADMIN_MASTERS = "admin/masters";
    private static final String CURRENT_USER = "currentUser";
    private static final String MASTERS = "masters";
    private static final String SEARCH = "search";
    private static final String SERVICE = "service";
    private static final String SERVICE_IDS = "serviceIds";

    private final IUserService userService;
    private final ISecurityService securityService;
    private final IMasterServicesService masterServicesService;

    public AdminMasterController(IUserService userService, ISecurityService securityService, IMasterServicesService masterServicesService) {
        this.userService = userService;
        this.securityService = securityService;
        this.masterServicesService = masterServicesService;
    }

    @GetMapping
    public String getMasters(Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute(MASTERS, this.userService.getAllMasters());
        model.addAttribute(SEARCH, new SearchDto());
        model.addAttribute(SERVICE, new MasterServiceDto());
        model.addAttribute(SERVICE_IDS, new ServiceIdsDto());
        return ADMIN_MASTERS;
    }

    @GetMapping("/search")
    public String searchMaster(@ModelAttribute(SEARCH) @Valid SearchDto searchDto, BindingResult bindingResult, Model model) {
        model.addAttribute(CURRENT_USER, getCurrentUser());
        model.addAttribute(SERVICE, new MasterServiceDto());
        model.addAttribute(SERVICE_IDS, new ServiceIdsDto());
        if (bindingResult.hasErrors()) {
            model.addAttribute(MASTERS, this.userService.getAllMasters());
            model.addAttribute(SEARCH, searchDto);
            model.addAttribute(SERVICE, new MasterServiceDto());
            model.addAttribute(SERVICE_IDS, new ServiceIdsDto());
            return ADMIN_MASTERS;
        }
        model.addAttribute(MASTERS, this.userService.searchMasters(searchDto.getText()));
        model.addAttribute(SEARCH, new SearchDto());
        return ADMIN_MASTERS;
    }

    @PostMapping("/delete")
    public String deleteServiceFromMaster(@ModelAttribute(SERVICE_IDS) ServiceIdsDto serviceIdsDto){
        this.masterServicesService.deleteServices(serviceIdsDto);
        return "redirect:/admin/masters";
    }

    @PostMapping("/{idMaster}")
    public String addServiceToMaster(@PathVariable(name = "idMaster") long idMaster,
                                     @ModelAttribute(SERVICE) MasterServiceDto masterServiceDto) {
        this.masterServicesService.createService(masterServiceDto, idMaster);
        return "redirect:/admin/masters";
    }

    private CurrentUserDto getCurrentUser() {
        return this.securityService.findLoggedInUser();
    }

}
