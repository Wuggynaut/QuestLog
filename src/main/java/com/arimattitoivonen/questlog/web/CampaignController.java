package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.*;
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

    public CampaignController(CampaignRepository campaignRepository, GameRepository gameRepository, AppUserRepository appUserRepository) {
        this.campaignRepository = campaignRepository;
        this.gameRepository = gameRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/campaignlist")
    public String getCampaigns(Model model) {
        model.addAttribute("campaigns", campaignRepository.findAll());
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
        model.addAttribute("campaign", campaignRepository.findById(id).orElseThrow());
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("statuses", Enums.CampaignStatus.values());
        return "editcampaign";
    }

    @PostMapping("savecampaign")
    public String saveCampaign (@ModelAttribute Campaign campaign) {
        campaignRepository.save(campaign);
        return "redirect:campaignlist";
    }

    @GetMapping("/deletecampaign/{id}")
    public String deleteCampaign(@PathVariable Long id, Model model) {
        campaignRepository.deleteById(id);
        return "redirect:../campaignlist";
    }
}
