package com.arimattitoivonen.questlog.web;

import com.arimattitoivonen.questlog.domain.AppUser;
import com.arimattitoivonen.questlog.domain.AppUserRepository;
import com.arimattitoivonen.questlog.domain.Enums;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppUserController {

    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/userlist")
    public String getUsers(Model model) {
        model.addAttribute("users", appUserRepository.findAll());
        return "userlist";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new AppUser());
        model.addAttribute("roles", Enums.UserRole.values());
        return "adduser";
    }

    @GetMapping("/edituser/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", appUserRepository.findById(id).orElseThrow());
        model.addAttribute("roles", Enums.UserRole.values());
        return "edituser";
    }

    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute AppUser user) {
        appUserRepository.save(user);
        return "redirect:userlist";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        appUserRepository.deleteById(id);
        return "redirect:../userlist";
    }
}
