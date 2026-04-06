package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.CampaignRepository;
import com.arimattitoivonen.questlog.domain.GameRepository;
import com.arimattitoivonen.questlog.domain.SessionRepository;
import org.springframework.stereotype.Controller;

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
}
