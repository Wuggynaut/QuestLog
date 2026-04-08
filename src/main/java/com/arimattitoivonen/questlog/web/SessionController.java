package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.CampaignRepository;
import com.arimattitoivonen.questlog.domain.GameRepository;
import com.arimattitoivonen.questlog.domain.Session;
import com.arimattitoivonen.questlog.domain.SessionRepository;
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

    public SessionController(SessionRepository sessionRepository, CampaignRepository campaignRepository, GameRepository gameRepository) {
        this.sessionRepository = sessionRepository;
        this.campaignRepository = campaignRepository;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/sessionlist")
    public String getSessions(Model model) {
        model.addAttribute("sessions", sessionRepository.findAll());
        return "sessionlist";
    }

    @GetMapping("/addsession")
    public String addSession(Model model) {
        model.addAttribute("session", new Session());
        model.addAttribute("campaigns", campaignRepository.findAll());
        model.addAttribute("games", gameRepository.findAll());
        return "addsession";
    }

    @GetMapping("/editsession/{id}")
    public String editSession(@PathVariable Long id, Model model) {
        model.addAttribute("session", sessionRepository.findById(id).orElseThrow());
        model.addAttribute("campaigns", campaignRepository.findAll());
        model.addAttribute("games", gameRepository.findAll());
        return "editsession";
    }

    @PostMapping("/savesession")
    public String saveSession(@ModelAttribute Session session) {
        sessionRepository.save(session);
        return "redirect:sessionlist";
    }

    @GetMapping("/deletesession/{id}")
    public String deleteSession(@PathVariable Long id) {
        sessionRepository.deleteById(id);
        return "redirect:../sessionlist";
    }
}
