package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arimattitoivonen.questlog.domain.Game;
import com.arimattitoivonen.questlog.domain.GameRepository;

@Controller
public class GameController {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

    // Constructor injection
    public GameController(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/gamelist")
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "gamelist";
    }

    @GetMapping("/addgame")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        return "addgame";
    }

    @PostMapping("/savegame")
    public String saveGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:gamelist";
    }

    @GetMapping("/deletegame/{id}")
    public String deleteGame(@PathVariable("id") Long id, Model model) {
        gameRepository.deleteById(id);
        return "redirect:../gamelist";
    }

    @GetMapping("/editgame/{id}")
    public String editGame(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id).orElseThrow());
        model.addAttribute("genres", genreRepository.findAll());
        return "editgame";
    }
}
