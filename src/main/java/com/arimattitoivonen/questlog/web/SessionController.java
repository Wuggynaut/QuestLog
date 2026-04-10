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
public class SessionController {

    private final SessionRepository sessionRepository;
    private final CampaignRepository campaignRepository;
    private final GameRepository gameRepository;
    private final AppUserService appUserService;

    public SessionController(SessionRepository sessionRepository, CampaignRepository campaignRepository, GameRepository gameRepository, AppUserService appUserService) {
        this.sessionRepository = sessionRepository;
        this.campaignRepository = campaignRepository;
        this.gameRepository = gameRepository;
        this.appUserService = appUserService;
    }

    @GetMapping("/sessionlist")
    public String getSessions(Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        model.addAttribute("sessions", sessionRepository.findByUser(currentUser));
        return "sessionlist";
    }

    @GetMapping("/addsession")
    public String addSession(Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        model.addAttribute("session", new Session());
        model.addAttribute("campaigns", campaignRepository.findByUser(currentUser));
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("roles", Enums.SessionRole.values());
        return "addsession";
    }

    @GetMapping("/editsession/{id}")
    public String editSession(@PathVariable Long id, Model model) {
        AppUser currentUser = appUserService.getCurrentUser();
        Session session = sessionRepository.findById(id).orElseThrow();
        if (!session.getUser().getId().equals(currentUser.getId())) {
            return "redirect:/sessionlist";
        }
        model.addAttribute("session", session);
        model.addAttribute("campaigns", campaignRepository.findByUser(currentUser));
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("roles", Enums.SessionRole.values());
        return "editsession";
    }

    @PostMapping("/savesession")
    public String saveSession(@ModelAttribute Session session) {
        AppUser currentUser = appUserService.getCurrentUser();
        session.setUser(currentUser);
        if (session.getCampaign() != null && session.getCampaign().getId() != null) {
            Campaign campaign = campaignRepository.findById(session.getCampaign().getId()).orElse(null);
            session.setCampaign(campaign);
            assert campaign != null;
            session.setGame(campaign.getGame()); // If session belongs to a campaign, always get the game field from the campaign.
        } else {
            session.setCampaign(null); // The session is a one-shot, and doesn't belong in a campaign
            Game game = gameRepository.findById(session.getGame().getId()).orElseThrow();
            session.setGame(game);
        }
        sessionRepository.save(session);
        return "redirect:sessionlist";
    }

    @GetMapping("/deletesession/{id}")
    public String deleteSession(@PathVariable Long id) {
        AppUser currentUser = appUserService.getCurrentUser();
        Session session = sessionRepository.findById(id).orElseThrow();
        if (!session.getUser().getId().equals(currentUser.getId())) {
            return "redirect:/sessionlist";
        }
        sessionRepository.deleteById(id);
        return "redirect:../sessionlist";
    }
}
