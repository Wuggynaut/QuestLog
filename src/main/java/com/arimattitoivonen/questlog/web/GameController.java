package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.Genre;
import com.arimattitoivonen.questlog.domain.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.arimattitoivonen.questlog.domain.Game;
import com.arimattitoivonen.questlog.domain.GameRepository;

import java.util.ArrayList;
import java.util.List;

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
    public String saveGame(@ModelAttribute Game game,
                           @RequestParam(value = "genreIds", required = false)List<Long> genreIds) {
        if (genreIds != null) {
            List<Genre> selectedGenres = new ArrayList<>();
            genreRepository.findAllById(genreIds).forEach(selectedGenres::add);
            game.setGenres(selectedGenres);
        } else {
            game.setGenres(new ArrayList<>());
        }
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
