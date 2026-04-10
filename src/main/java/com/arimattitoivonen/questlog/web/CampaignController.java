package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.*;
import com.arimattitoivonen.questlog.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CampaignController {

    private final CampaignRepository campaignRepository;
    private final GameRepository gameRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    public CampaignController(CampaignRepository campaignRepository, GameRepository gameRepository, AppUserRepository appUserRepository, AppUserService appUserService) {
        this.campaignRepository = campaignRepository;
        this.gameRepository = gameRepository;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @GetMapping("/campaignlist")
    public String getCampaigns(Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        if (currentUser.getRole() == Enums.UserRole.ADMIN) {
            model.addAttribute("campaigns", campaignRepository.findAll());
        } else {
            model.addAttribute("campaigns", campaignRepository.findByUser(currentUser));
        }
        return "campaignlist";
    }
    
    @GetMapping("/addcampaign")
    public String addCampaign(Model model) {
        model.addAttribute("campaign", new Campaign());
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("users", appUserRepository.findAll());
        model.addAttribute("statuses", Enums.CampaignStatus.values());
        return "addcampaign";
    }

    @GetMapping("/editcampaign/{id}")
    public String editCampaign(@PathVariable Long id, Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        Campaign campaign = campaignRepository.findById(id).orElseThrow();
        if (currentUser.getRole() != Enums.UserRole.ADMIN && !campaign.getUser().getId().equals(currentUser.getId())) {
            return "redirect:/campaignlist";
        }
        model.addAttribute("campaign", campaign);
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("statuses", Enums.CampaignStatus.values());
        return "editcampaign";
    }

    @PostMapping("savecampaign")
    public String saveCampaign (@ModelAttribute Campaign campaign) {
        AppUser currentUser = appUserService.getCurrentUser();
        if (campaign.getId() == null || campaign.getId() == 0) {
            campaign.setUser(currentUser);
        } else {
            Campaign existing = campaignRepository.findById(campaign.getId()).orElseThrow();
            campaign.setUser(existing.getUser());
        }
        campaignRepository.save(campaign);
        return "redirect:campaignlist";
    }

    @GetMapping("/deletecampaign/{id}")
    public String deleteCampaign(@PathVariable Long id, Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        Campaign campaign = campaignRepository.findById(id).orElseThrow();
        if (currentUser.getRole() != Enums.UserRole.ADMIN && !campaign.getUser().getId().equals(currentUser.getId())) {
            return "redirect:/campaignlist";
        }
        campaignRepository.deleteById(id);
        return "redirect:../campaignlist";
    }
}
