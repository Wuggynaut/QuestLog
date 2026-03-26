package com.arimattitoivonen.questlog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.arimattitoivonen.questlog.domain.Game;
import com.arimattitoivonen.questlog.domain.GameRepository;

@Controller
public class GameController {

    private GameRepository gameRepository;

    // Constructor injection
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/gamelist")
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "gamelist";
    }

    @GetMapping("/addgame")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        return "addgame";
    }

    @PostMapping("/savegame")
    public String saveGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:gamelist";
    }
}
