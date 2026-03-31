package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.Genre;
import com.arimattitoivonen.questlog.domain.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController (GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/genrelist")
    public String getGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genrelist";
    }

    @GetMapping("/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }

    @PostMapping("/savegenre")
    public String saveGenre(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:genrelist";
    }

    @GetMapping("/deletegenre/{id}")
    public String deleteGenre(@PathVariable("id") Long id, Model model) {
        genreRepository.deleteById(id);
        return "redirect:../genrelist";
    }

    @GetMapping("/editgenre/{id}")
    public String editGenre(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id));
        return "editgenre";
    }
}
